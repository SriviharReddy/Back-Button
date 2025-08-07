package com.example.back.presentation

import android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_BACK // Add this import
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils.SimpleStringSplitter
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isAccessibilityServiceEnabled(this)) {
            // If permission is already granted, tell the service to perform the back action.
            BackPressService.instance?.performGlobalAction(GLOBAL_ACTION_BACK)
        } else {
            // If permission is NOT granted, go to settings for the one-time setup.
            Toast.makeText(this, "Enable 'Back' app service", Toast.LENGTH_SHORT).show()
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }

        // Close this invisible activity immediately in all cases.
        finish()
    }

    private fun isAccessibilityServiceEnabled(context: Context): Boolean {
        val service = "${context.packageName}/${BackPressService::class.java.canonicalName}"
        try {
            val enabledServices = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            )
            val splitter = SimpleStringSplitter(':')
            splitter.setString(enabledServices)
            while (splitter.hasNext()) {
                if (splitter.next().equals(service, ignoreCase = true)) {
                    return true
                }
            }
        } catch (e: Exception) {
            return false
        }
        return false
    }
}
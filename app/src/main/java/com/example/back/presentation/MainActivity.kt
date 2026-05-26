package com.example.back.presentation

import android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_BACK
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils.SimpleStringSplitter
import android.widget.Toast
import com.example.back.R

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isAccessibilityServiceEnabled(this)) {
            val service = BackPressService.instance
            if (service?.performGlobalAction(GLOBAL_ACTION_BACK) != true) {
                Toast.makeText(this, R.string.service_not_ready, Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, R.string.enable_service_prompt, Toast.LENGTH_SHORT).show()
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }

        finish()
    }

    private fun isAccessibilityServiceEnabled(context: Context): Boolean {
        val service = "${context.packageName}/${BackPressService::class.java.canonicalName}"
        val enabledServices = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false

        return try {
            val splitter = SimpleStringSplitter(':')
            splitter.setString(enabledServices)
            while (splitter.hasNext()) {
                if (splitter.next().equals(service, ignoreCase = true)) {
                    return true
                }
            }
            return false
        } catch (_: Exception) {
            false
        }
    }
}

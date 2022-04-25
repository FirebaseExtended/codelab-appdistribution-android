package com.google.firebase.appdistributioncodelab.kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.appdistribution.FirebaseAppDistribution
import com.google.firebase.appdistribution.ktx.appDistribution
import com.google.firebase.appdistributioncodelab.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAppDistribution: FirebaseAppDistribution
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAppDistribution = Firebase.appDistribution
        binding.updatebutton.setOnClickListener { view ->
            checkForUpdate()
        }
        binding.signinButton.setOnClickListener { view ->
            signIn()
        }
    }

    private fun checkForUpdate() {
    }

    private fun signIn() {
    }

    private fun isTesterSignedIn() : Boolean {
        return false
    }

    private fun configureUpdateButton() {
        binding.updatebutton.visibility = if (isTesterSignedIn()) View.VISIBLE else View.GONE
    }

    private fun configureSigninButton() {
        binding.signinButton.text = if (isTesterSignedIn()) "Sign Out" else "Sign In"
        binding.signinButton.visibility = View.VISIBLE
    }

    companion object {
        private const val TAG = "AppDistribution-Codelab"
    }
}

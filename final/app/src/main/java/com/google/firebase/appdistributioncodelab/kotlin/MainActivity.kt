package com.google.firebase.appdistributioncodelab.kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.appdistribution.FirebaseAppDistribution
import com.google.firebase.appdistribution.FirebaseAppDistributionException
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



    override fun onResume() {
        super.onResume()
        configureUpdateButton()
        configureSigninButton()
    }

    private fun checkForUpdate() {
        firebaseAppDistribution.updateIfNewReleaseAvailable()
            .addOnProgressListener { updateProgress ->
                // (Optional) Implement custom progress updates in addition to
                // automatic NotificationManager updates.
            }
            .addOnFailureListener { e ->
                if (e is FirebaseAppDistributionException) {
                    // Handle exception.
                }
            }
    }

    private fun signIn() {
        if (isTesterSignedIn()) {
            firebaseAppDistribution.signOutTester()
            configureUpdateButton()
            configureSigninButton()
        } else {
            firebaseAppDistribution.signInTester()
        }
    }

    private fun isTesterSignedIn() : Boolean {
        return firebaseAppDistribution.isTesterSignedIn
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

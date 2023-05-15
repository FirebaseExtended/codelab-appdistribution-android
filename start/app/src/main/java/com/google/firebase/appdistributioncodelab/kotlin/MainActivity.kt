/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.firebase.appdistributioncodelab.kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.appdistribution.FirebaseAppDistribution
import com.google.firebase.appdistribution.ktx.appDistribution
import com.google.firebase.appdistributionquickstart.databinding.ActivityMainBinding
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

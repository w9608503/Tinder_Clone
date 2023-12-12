package com.thecode.tinderclone.Firebase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.thecode.tinderclone.R
import com.thecode.tinderclone.activities.MainActivity

class LoginActivity : ComponentActivity() {
    private var firebaseAuth: FirebaseAuth? = null
    private lateinit var loginEmailEditText: EditText
    private lateinit var loginPasswordEditText: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Get the instance of the Firebase App
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is already authenticated
        if (firebaseAuth!!.currentUser != null) {
            // User is already signed in, redirect to the main part of your app
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish() // Finish the SignUpActivity
        }

        setContent {
            Login(
                onLoginClick = { email, password ->
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        login(email.trim { it <= ' ' }, password.trim { it <= ' ' })
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Please fill out all the fields.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                onSignUpClick = { signup() }
            )
        }
    }

    private fun login(
        email: String,
        password: String
    ) {
        firebaseAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@LoginActivity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI or navigate to the next activity
                    Toast.makeText(this@LoginActivity, "Login successful!", Toast.LENGTH_SHORT)
                        .show()
                    val i = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(i)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this@LoginActivity, "Login failed. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun signup() {
        val i = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(i)
    }
}


@Preview(showBackground = true)
@Composable
fun Login(
    onLoginClick: (String, String) -> Unit = { _, _ -> },
    onSignUpClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF333333))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Text(text = "LOGIN", color = Color.White, fontSize = 40.sp)
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            shape = RoundedCornerShape(32.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            placeholder = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password, onValueChange = { password = it },
            shape = RoundedCornerShape(32.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            ),
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text(text = "Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onLoginClick(email, password) },
                modifier = Modifier.weight(1f),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC53E3E))
            ) {
                Text(text = "LOGIN")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { onSignUpClick() },
                modifier = Modifier.weight(1f),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC53E3E))
            ) {
                Text(text = "SIGNUP")
            }
        }
    }
}
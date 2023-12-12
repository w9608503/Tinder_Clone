package com.thecode.tinderclone.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.thecode.tinderclone.Firebase.LoginActivity
import com.thecode.tinderclone.Firebase.UsersActivity
import com.thecode.tinderclone.R
import com.thecode.tinderclone.adapters.SliderAdapter
import de.hdodenhof.circleimageview.CircleImageView

class AccountFragment : Fragment() {
    lateinit var rootLayout: View
    private lateinit var sliderView: SliderView
    lateinit var email: TextView
    lateinit var username: TextView
    lateinit var address: TextView
    lateinit var logout: LinearLayout
    lateinit var profile: CircleImageView
    lateinit var userlist: LinearLayout
    var firebaseAuth: FirebaseAuth? = null
    var databaseReference: DatabaseReference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootLayout = inflater.inflate(R.layout.fragment_account, container, false)
        email = rootLayout.findViewById(R.id.email)
        username = rootLayout.findViewById(R.id.usName)
        logout = rootLayout.findViewById(R.id.logout)
        profile=rootLayout.findViewById(R.id.profile_image)
        userlist = rootLayout.findViewById(R.id.userlist)
        address = rootLayout.findViewById(R.id.address)
        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        logout.setOnClickListener {
            // Call the logout function
            logout()
        }
        userlist.setOnClickListener {
            val intent = Intent(activity, UsersActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity?.finish()
        }

        if (firebaseAuth!!.currentUser != null) {
            // User is already signed in, retrieve user information
            val userId = firebaseAuth!!.currentUser!!.uid
            retrieveUserInfo(userId)
        }

        sliderView = rootLayout.findViewById(R.id.slider_view)
        val adapter = activity?.let { SliderAdapter(it) }
        sliderView.setSliderAdapter(adapter)
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE)
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT)
        sliderView.startAutoCycle()

        return rootLayout
    }
    private fun logout() {
        firebaseAuth?.signOut()

        // Navigate to the login activity
        val intent = Intent(activity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        activity?.finish()
    }
    private fun retrieveUserInfo(userId: String) {
        val userRef = databaseReference?.child("users")?.child(userId)

        userRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = snapshot.getValue(User::class.java)
                    // Update UI with user information
                    email.text = user?.email
                    username.text = user?.username
                    address.text = user?.address
                    context?.let {
                        Glide.with(it)
                            .load(user?.imageUrl)
                            .into(profile)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}

data class User(
    val email: String? = null,
    val username: String? = null,
    val address: String? = null,
    val imageUrl: String? = null // Add this field for storing the image URL

)

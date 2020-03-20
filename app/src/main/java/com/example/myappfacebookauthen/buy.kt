package com.example.myappfacebookauthen


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_buy.*

/**
 * A simple [Fragment] subclass.
 */
class buy : Fragment() {

    lateinit var textview : TextView
    lateinit var button: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_buy, container, false)
        // Inflate the layout for this fragment

        //Instantiate new instance of our class
        val getRequest = HttpGetRequest()

        val submit = view.findViewById<Button>(R.id.submit)

        val mRootRef = FirebaseDatabase.getInstance().getReference()

        val mUsersRef = mRootRef.child("name")
        val mMessagesRef = mRootRef.child("phone number")

        button = view.findViewById(R.id.submit)

        button.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("What greater gift than the love of a cat. Right?")
            builder.setPositiveButton("Yes",
                DialogInterface.OnClickListener{ dialog, id ->
                    Toast.makeText(this.context, "Time spent with cats is never wasted. Thank you", Toast.LENGTH_SHORT).show()
                    val mMessagesRef2 = mRootRef.child("Customer")

                    val key = mMessagesRef.push().key
                    val postValues: HashMap<String, Any> = HashMap()
                    postValues["name"] = "Rungsiya Charoenlarp"
                    postValues["phonenumber"] = "089-931-7994"

                    val childUpdates: MutableMap<String, Any> = HashMap()

                    childUpdates["$key"] = postValues

                    mMessagesRef2.updateChildren(childUpdates)
                })
            builder.setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
            builder.show()
        }

        textview = view.findViewById(R.id.text)

        return view
    }

    data class FriendlyMessage(
        var username: String? = "",
        var text: String? = ""
    )

}

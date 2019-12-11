package bonch.dev.team4_application.models

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

data class Statistics(val id: Int, val titleObject: String, val progress: String)

class StatisticsLab() {

    val statisticsList : MutableList<Statistics>

    private var mAuth: FirebaseAuth
    private var mDatabase: FirebaseDatabase
    private var mReference: DatabaseReference

    init {


        mAuth = FirebaseAuth.getInstance()

        val subject = mAuth.currentUser!!.uid

        mDatabase = FirebaseDatabase.getInstance()
        mReference = mDatabase.reference.child("Exact sciences").child("Algebra")


        statisticsList = mutableListOf()
        for (i in 0..1) {

            mReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val statistic = Statistics(i, "Предмет: ${p0.child("Algebra").value as String}",
                        "Прогресс: ${p0.child("count").value as String}")
                    print(p0.child("Algebra").value as String)
                    statisticsList.add(statistic)
                }

                override fun onCancelled(p0: DatabaseError) {
                    //
                }
            })

        }
    }

}
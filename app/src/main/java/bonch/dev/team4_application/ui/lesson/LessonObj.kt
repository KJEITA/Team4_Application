package bonch.dev.team4_application.ui.lesson

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

data class LessonObj(
    val lessonId: Int,
    val lessonName: String,
    val lessonAll: String,
    val lessonOpen: String,
    val lessonProgress: String,
    val lessonType: Int
) {
    class LessonLab {
        val messageList: MutableList<LessonObj>
        val progressList: MutableList<Int>


        private var mDataBase: FirebaseDatabase = FirebaseDatabase.getInstance()
        private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

        private var mReference: DatabaseReference
        private var mReference2: DatabaseReference
        var rec: Lesson_Recycler_item? = null

        init {
            mReference = mDataBase.reference.child("Exact sciences").child("Algebra")
            mReference2 =
                mDataBase.reference.child("Users").child(mAuth.currentUser!!.uid).child("Progress")
                    .child("Algebra")

            messageList = mutableListOf()
            progressList = mutableListOf()

            var lesson = LessonObj(0, "Алгебра", "42", "3", "1%", 0)
            messageList.add(lesson)

            mReference2.addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        var count = p0.childrenCount
                        for (i in 1..count)
                            progressList.add((p0.child(i.toString()).value as Long).toInt())

                    }
                })

            mReference.addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        if (rec != null) {

                            val count = p0.childrenCount.toInt()
                            val progress = progressList.size

                            messageList.clear()

                            var lesson = LessonObj(
                                0,
                                "Алгебра",
                                count.toString(),
                                progress.toString(),
                                ((progress.toDouble() / count.toDouble()) * 100).toInt().toString() + "%",
                                0
                            )
                            messageList.add(lesson)

                            for (i in 1..count) {
                                var lessType =
                                    if (progressList.contains(i))
                                        2
                                    else
                                        1

                                val name = p0.child(i.toString()).child("Name").value as String
                                var lesson = LessonObj(i, name, "1", "1", "1", lessType)
                                messageList.add(lesson)
                            }
                            rec!!.updateData()
                            rec!!.notifyDataSetChanged()
                        }
                    }
                })
        }

        fun setRecc(r: Lesson_Recycler_item) {
            rec = r
        }
    }
}
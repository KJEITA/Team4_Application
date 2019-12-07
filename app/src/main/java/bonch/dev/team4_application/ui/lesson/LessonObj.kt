package bonch.dev.team4_application.ui.lesson

import com.google.firebase.database.*

data class LessonObj(
    val lessonId: Int,
    val lessonName: String,
    val lessonAll: String,
    val lessonOpen: String,
    val lessonProgress: String,
    val lessonType: Int
) {
    class LessonLab() {
        val messageList: MutableList<LessonObj>

        private lateinit var mDataBase: FirebaseDatabase
        private lateinit var mReference: DatabaseReference
        var rec: Lesson_Recycler_item? = null

        init {
            mDataBase = FirebaseDatabase.getInstance()
            mReference = mDataBase.reference.child("Exact sciences").child("Algebra")



            messageList = mutableListOf()
            var lesson = LessonObj(0, "Алгебра", "42", "3", "1%", 0)
            messageList.add(lesson)

            mReference.addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {

                        if (rec != null) {

                            val count = (p0.child("Count").value as Long).toInt()

                            for (i in 1..count) {
                                val name = p0.child(i.toString()).child("Name").value as String
                                var lesson = LessonObj(i, name, "1", "1", "1", 1)
                                messageList.add(lesson)
                            }
                            rec!!.updateData()
                            rec!!.notifyDataSetChanged()
                        }
                    }
                })


            /*messageList = mutableListOf()
            //var b = true
            var lesson = LessonObj(0, "Алгебра", "42", "3", "1%", 0)
            messageList.add(lesson)
            for (i in 1..20) {
                var lesson = LessonObj(i, "Степени и корни", "42", "3", "1%", 1)
                messageList.add(lesson)
            }*/
        }

        public fun setRecc(r: Lesson_Recycler_item) {
            rec = r
        }
    }
}
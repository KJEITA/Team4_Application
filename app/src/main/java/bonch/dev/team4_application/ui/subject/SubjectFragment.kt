package bonch.dev.team4_application.ui.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.team4_application.R
import bonch.dev.team4_application.adapters.SubjSectAdapter
import bonch.dev.team4_application.model.SubjSect
import bonch.dev.team4_application.model.Subject

class SubjectFragment : Fragment() {

    private lateinit var subjSectRV : RecyclerView
    private lateinit var subjList : MutableList<Subject>
    private lateinit var subjList1 : MutableList<Subject>
    private lateinit var subjSectList : MutableList<SubjSect>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_subject, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subjSectRV = view.findViewById(R.id.subjectsSectionRV)
        initSubjSectList()
        setRvData()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun initSubjSectList() {
        subjList = mutableListOf()
        subjList1 = mutableListOf()
        subjSectList = mutableListOf()

        subjList.add(Subject("PAID 1","https://55341418bc55394fbe0f-65d6d0e87ce8126fb80e16752287ad6c.ssl.cf1.rackcdn.com/d927be24-3e49-11e8-83a9-08606e697db0/large.png"))
        subjList.add(Subject("PAID 2","https://55341418bc55394fbe0f-65d6d0e87ce8126fb80e16752287ad6c.ssl.cf1.rackcdn.com/d927be24-3e49-11e8-83a9-08606e697db0/large.png"))
        subjList.add(Subject("PAID 3","https://55341418bc55394fbe0f-65d6d0e87ce8126fb80e16752287ad6c.ssl.cf1.rackcdn.com/d927be24-3e49-11e8-83a9-08606e697db0/large.png"))
        subjList.add(Subject("PAID 4","https://55341418bc55394fbe0f-65d6d0e87ce8126fb80e16752287ad6c.ssl.cf1.rackcdn.com/d927be24-3e49-11e8-83a9-08606e697db0/large.png"))
        subjList.add(Subject("PAID 5","https://55341418bc55394fbe0f-65d6d0e87ce8126fb80e16752287ad6c.ssl.cf1.rackcdn.com/d927be24-3e49-11e8-83a9-08606e697db0/large.png"))
        subjList.add(Subject("PAID 6","https://55341418bc55394fbe0f-65d6d0e87ce8126fb80e16752287ad6c.ssl.cf1.rackcdn.com/d927be24-3e49-11e8-83a9-08606e697db0/large.png"))
        subjList.add(Subject("PAID 7","https://55341418bc55394fbe0f-65d6d0e87ce8126fb80e16752287ad6c.ssl.cf1.rackcdn.com/d927be24-3e49-11e8-83a9-08606e697db0/large.png"))
        subjList.add(Subject("PAID 8","https://55341418bc55394fbe0f-65d6d0e87ce8126fb80e16752287ad6c.ssl.cf1.rackcdn.com/d927be24-3e49-11e8-83a9-08606e697db0/large.png"))

        subjList1.add(Subject("EXACT 1","https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/dc/fb/ce/dcfbce3b-7c05-776b-ae89-27aaf35a0462/source/100x100bb.jpg"))
        subjList1.add(Subject("EXACT 1 EXACT 3","https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/dc/fb/ce/dcfbce3b-7c05-776b-ae89-27aaf35a0462/source/100x100bb.jpg"))
        subjList1.add(Subject("EXACT 3 EXACT 5","https://is2-ssl.mzstatic.com/image/thumb/Purple124/v4/dc/fb/ce/dcfbce3b-7c05-776b-ae89-27aaf35a0462/source/100x100bb.jpg"))
        subjSectList.add(SubjSect("PAID SECT",0,subjList))
        subjSectList.add(SubjSect("EXACT SECT SCIENCES",1,subjList1))
        subjSectList.add(SubjSect("PAID SECT1",0,subjList))
    }

    private fun setRvData() {
        subjSectRV.layoutManager = LinearLayoutManager(view!!.context)
        subjSectRV.adapter= SubjSectAdapter(subjSectList)
    }
}
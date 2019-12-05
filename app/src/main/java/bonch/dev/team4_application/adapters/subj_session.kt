package bonch.dev.team4_application.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.team4_application.R
import bonch.dev.team4_application.model.Constants
import bonch.dev.team4_application.model.SubjSect
import com.bumptech.glide.Glide


class SubjSectAdapter(subjSectList: MutableList<SubjSect>) :
    RecyclerView.Adapter<SubjSectAdapter.SubjSectHolder>() {

    val subjSectList = subjSectList

    override fun getItemViewType(position: Int): Int {

        val type = when (subjSectList[position].subjSectType) {
            0 -> Constants.PAID_SUBJ
            1 -> Constants.EXACT_SUBJ
            2 -> Constants.TECH_SUBJ
            3 -> Constants.NATURAL_SUBJ
            4 -> Constants.HUMAN_SUBJ
            else -> Constants.NOT_FOUND_SUBJ
        }
        return type

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjSectHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_item_section, parent, false)
        return SubjSectHolder(view)
    }

    override fun getItemCount(): Int {
        return subjSectList.size
    }


    override fun onBindViewHolder(holder: SubjSectHolder, position: Int) {
        holder.bind(position, holder.itemViewType, holder.itemView)

    }

    inner class SubjSectHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int, viewType: Int, view: View) {

            val paidSubjTableLayout = itemView.findViewById<TableLayout>(R.id.subjTableLayout)
            val listRow: MutableList<TableRow> = mutableListOf()

            val titleSubjSect: TextView =
                view.findViewById(R.id.subjSectTitleTextView)
            titleSubjSect.setText(subjSectList.get(position).subjSectTitle)

            for (j in 0..(Math.ceil((subjSectList.get(position).subjList.size / 2).toDouble()).toInt())) {
                listRow.add(j, TableRow(view.context))
                listRow[j].layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
                )
                paidSubjTableLayout.addView(listRow.get(j))


                if (viewType == Constants.PAID_SUBJ) {
                    for (i: Int in 0..subjSectList.get(position).subjList.size - 1) {

                        addSubjItem(i, j, position, view, listRow,"#D81B60")
                    }
                }



                if (viewType == Constants.EXACT_SUBJ) {

                    for (i: Int in 0..subjSectList.get(position).subjList.size - 1) {

                        addSubjItem(i, j, position, view, listRow,"#008577")
                    }
                }

                if (viewType == Constants.TECH_SUBJ) {
                    for (i: Int in 0..subjSectList.get(position).subjList.size - 1) {

                        addSubjItem(i, j, position, view, listRow,"#D81B60")
                    }
                }

                if (viewType == Constants.NATURAL_SUBJ) {
                    for (i: Int in 0..subjSectList.get(position).subjList.size - 1) {

                        addSubjItem(i, j, position, view, listRow,"#D81B60")
                    }
                }

                if (viewType == Constants.HUMAN_SUBJ) {
                    for (i: Int in 0..subjSectList.get(position).subjList.size - 1) {

                        addSubjItem(i, j, position, view, listRow,"#D81B60")
                    }
                }

                if (viewType == Constants.NOT_FOUND_SUBJ) {
                    for (i: Int in 0..subjSectList.get(position).subjList.size - 1) {

                        addSubjItem(i, j, position, view, listRow,"#D81B60")
                    }
                }


            }

        }

    }

    private fun addSubjItem(i: Int, j: Int, position: Int, view: View,
                            listRow: MutableList<TableRow>,
                            color:String) {

        if ((i == j * 2) || (i == j * 2 + 1)) {

            val inflater = LayoutInflater.from(view.context)

            val subjItemLayout: View =
                inflater.inflate(R.layout.subject_item, view as ViewGroup, false)

            val subjTitleTextView: TextView =
                subjItemLayout.findViewById(R.id.subjTitleTextView)
            val subjImageView: ImageView =
                subjItemLayout.findViewById(R.id.subjImageView)

            subjItemLayout.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, 1f
            )
            subjItemLayout.setBackgroundColor(Color.parseColor(color))

            Glide.with(view.context)
                .load(subjSectList.get(position).subjList[i].imageURL)
                .into(subjImageView)
            subjTitleTextView.setText(subjSectList.get(position).subjList.get(i).nameTitle)

            listRow[j].addView(subjItemLayout)
        }

    }
}

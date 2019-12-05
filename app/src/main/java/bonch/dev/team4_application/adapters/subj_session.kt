package bonch.dev.team4_application.adapters

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
            for (i: Int in 1..subjSectList.get(position).subjList.size) {

                if (viewType == Constants.PAID_SUBJ) {

                    if (i%2==0) {
                        val tableRow = TableRow(view.context)
                        val inflater = LayoutInflater.from(view.context)

                        val firstSubjItemLayout: View =
                            inflater.inflate(R.layout.subject_item, view as ViewGroup, false)
                        val secondSubjItemLayout: View =
                            inflater.inflate(R.layout.subject_item, view as ViewGroup, false)

                        val titleSubjSect : TextView = view.findViewById(R.id.subjSectTitleTextView)
                        val firstTextView : TextView =firstSubjItemLayout.findViewById(R.id.subjTitleTextView)
                        val secondTextView : TextView =secondSubjItemLayout.findViewById(R.id.subjTitleTextView)
                        val firstImageView : ImageView = firstSubjItemLayout.findViewById(R.id.subjImageView)
                        val secondImageView : ImageView = secondSubjItemLayout.findViewById(R.id.subjImageView)

                        titleSubjSect.setText(subjSectList.get(position).subjSectTitle)

                        firstSubjItemLayout.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT,1f)

                        secondSubjItemLayout.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT,1f)


                        Glide.with(view.context)
                            .load(subjSectList[position].subjList[i-1].imageURL)
                            .into(firstImageView)

                        Glide.with(view.context)
                            .load(subjSectList[position].subjList[i].imageURL)
                            .into(secondImageView)

                        firstTextView.setText(subjSectList.get(position).subjList.get(i-1).nameTitle)
                        secondTextView.setText(subjSectList.get(position).subjList.get(i).nameTitle)

                        tableRow.layoutParams= TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT)
                        tableRow.addView(firstSubjItemLayout)
                        tableRow.addView(secondSubjItemLayout)
                        paidSubjTableLayout.addView(tableRow)


                    }


                }

                if (viewType == Constants.EXACT_SUBJ) {

                }

                if (viewType == Constants.TECH_SUBJ) {

                }

                if (viewType == Constants.NATURAL_SUBJ) {

                }

                if (viewType == Constants.HUMAN_SUBJ) {

                }

                if (viewType == Constants.NOT_FOUND_SUBJ) {

                }

            }

        }

    }
}
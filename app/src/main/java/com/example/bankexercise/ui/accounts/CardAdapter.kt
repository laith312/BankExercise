package com.example.bankexercise.ui.accounts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bankexercise.R
import com.example.bankexercise.data.Card

class CardAdapter(private val cards: List<Card>) : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        with(holder.itemView) {
            findViewById<TextView>(R.id.cardName).text =
                cards[position].cardName

            setOnClickListener {
                onItemClickListener?.let {
                    it(cards[position])
                }
            }
        }
    }

    private var onItemClickListener: ((Card) -> Unit)? = null

    fun setOnItemClickListener(listener: (Card) -> Unit) {
        onItemClickListener = listener
    }
}

/**
 * Holds the view for the Card item.
 */
class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
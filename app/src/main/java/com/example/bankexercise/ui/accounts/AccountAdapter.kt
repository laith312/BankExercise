package com.example.bankexercise.ui.accounts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bankexercise.R
import com.example.bankexercise.data.Account
import com.example.bankexercise.data.AccountType

class AccountAdapter(private val accounts: List<Account>) :
    RecyclerView.Adapter<AccountItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountItemViewHolder {
        return AccountItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_account, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AccountItemViewHolder, position: Int) {
        with(holder.itemView) {
            findViewById<TextView>(R.id.accountName).text =
                accounts[position].accountType.toString()

            // TODO make this string internationalized
            findViewById<TextView>(R.id.accountAmount).text =
                "$" + accounts[position].accountBalance.toString()

            when (accounts[position].accountType) {
                AccountType.CHECKING -> {
                    findViewById<ImageView>(R.id.accountImage)
                        .setImageResource(R.drawable.ic_checking_round_24dp)
                }

                AccountType.MY_FAMILY -> {
                    findViewById<ImageView>(R.id.accountImage)
                        .setImageResource(R.drawable.ic_family_round_24dp)
                }

                AccountType.SAVINGS -> {
                    findViewById<ImageView>(R.id.accountImage)
                        .setImageResource(R.drawable.ic_savings_round_24dp)
                }
            }

            setOnClickListener {
                onItemClickListener?.let {
                    it(accounts[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    private var onItemClickListener: ((Account) -> Unit)? = null

    fun setOnItemClickListener(listener: (Account) -> Unit) {
        onItemClickListener = listener
    }
}

/**
 * Holds the view for the Account item.
 */
class AccountItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
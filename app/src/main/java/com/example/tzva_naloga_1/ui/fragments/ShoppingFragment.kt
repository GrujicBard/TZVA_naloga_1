package com.example.tzva_naloga_1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tzva_naloga_1.R
import com.example.tzva_naloga_1.adapters.ItemListAdapter
import com.example.tzva_naloga_1.database.*
import com.example.tzva_naloga_1.database.entities.ItemEntity
import com.example.tzva_naloga_1.ui.dialog_fragments.ShoppingItemDialogFragment

class ShoppingFragment : Fragment(), ItemListAdapter.OnItemClickListener, ItemListAdapter.OnItemLongClickListener {

    private val shoppingItemViewModel: ShoppingItemViewModel by viewModels {
        ShoppingItemViewModelFactory((activity?.application as ItemsApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_database, container, false);

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview);
        val adapter = ItemListAdapter(this, this)



        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        shoppingItemViewModel.allShoppingItems.observe(viewLifecycleOwner) { items ->
            items.let { adapter.submitList(it) }
        }

        return view;
    }
    //Open dialog on recyclerview Item click
    override fun onItemClick(item: ItemEntity) {
        val itemDialog = ShoppingItemDialogFragment(item)
        itemDialog.show(parentFragmentManager, "ShoppingItemDialog")
    }

    override fun onItemLongClick(item: ItemEntity) {
        Toast.makeText(requireContext(), "Long click!", Toast.LENGTH_SHORT).show()
    }
}
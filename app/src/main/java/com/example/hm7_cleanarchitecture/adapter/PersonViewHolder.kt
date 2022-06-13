package com.example.hm7_cleanarchitecture.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import com.example.hm7_cleanarchitecture.databinding.ItemPersonBinding
import com.example.hm7_cleanarchitecture.model.Person
import com.example.hm7_cleanarchitecture.model.ItemType


class PersonViewHolder(
    private val binding: ItemPersonBinding,
    private val onUserClicked: (ItemType<Person>) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(person: ItemType<Person>) {

        val newPerson = person as ItemType.Content

        with(binding) {
            imageView.load(newPerson.data.imageApi) {
                scale(Scale.FILL)
                size(ViewSizeResolver(root))
            }

            idPerson.text = newPerson.data.idApi.toString()
            textNameView.text = newPerson.data.nameApi
            root.setOnClickListener {
                onUserClicked(newPerson)
            }
        }

    }
}
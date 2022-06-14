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
    private val onUserClicked: (Person) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(person: Person) {

        with(binding) {
            imageView.load(person.imageApi) {
                scale(Scale.FILL)
                size(ViewSizeResolver(root))
            }

            idPerson.text = person.idApi.toString()
            textNameView.text = person.nameApi
            root.setOnClickListener {
                onUserClicked(person)
            }
        }

    }
}
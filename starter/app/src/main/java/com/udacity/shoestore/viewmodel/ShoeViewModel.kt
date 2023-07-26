package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.AddShoeState
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {
    private val _shoeListLiveData = MutableLiveData<MutableList<Shoe>>()
    val shoeListLiveData: LiveData<MutableList<Shoe>>
        get() = _shoeListLiveData


    private val _addShoeStateLiveData = MutableLiveData<AddShoeState>()
    val addShoeStateLiveData: LiveData<AddShoeState>
        get() = _addShoeStateLiveData

    init {
        _shoeListLiveData.value = mutableListOf()
        _addShoeStateLiveData.value = AddShoeState.NOT_DEFINED
        addInitialShoes()
    }

    private fun addInitialShoes() {
        addShoe("Pegasus", 9.0, "Nike", "Running Shoe")
        addShoe("Boat", 8.0, "Adidas", "Sports Shoe")
    }

    fun addNewShoe(name: String, company: String, size: String, description: String) {
        if (isValidShoe(name, company, size, description)) {
            _addShoeStateLiveData.value = AddShoeState.VALID
            addShoe(
                name,
                size.toDouble(),
                company,
                description
            )
        } else {
            _addShoeStateLiveData.value = AddShoeState.INVALID
        }
    }

    fun isValidShoe(name: String, company: String, size: String, description: String): Boolean {
        return name.isNotEmpty() && size.isNotEmpty() && company.isNotEmpty() && description.isNotEmpty()
    }

    private fun addShoe(name: String, size: Double, company: String, description: String) {
        val shoe = Shoe(name, size, company, description)
        _shoeListLiveData.value?.add(shoe)
    }

    fun updateState(state: AddShoeState) {
        _addShoeStateLiveData.value = state
    }
}
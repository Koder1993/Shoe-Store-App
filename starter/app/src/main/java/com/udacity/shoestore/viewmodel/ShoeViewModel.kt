package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.AddShoeState
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {
    private var _shoeListLiveData = MutableLiveData<List<Shoe>>()
    var shoeListLiveData: LiveData<List<Shoe>> = _shoeListLiveData

    private val _addShoeStateLiveData = MutableLiveData<AddShoeState>()
    val addShoeStateLiveData: LiveData<AddShoeState>
        get() = _addShoeStateLiveData

    init {
        _addShoeStateLiveData.value = AddShoeState.NOT_DEFINED
        addInitialShoes()
    }

    private fun addInitialShoes() {
        addShoe("Pegasus", "9", "Nike", "Running Shoe")
        addShoe("Boat", "8", "Adidas", "Sports Shoe")
    }

    fun addNewShoe(shoe: Shoe) {
        if (isValidShoe(shoe)) {
            addShoe(shoe)
            _addShoeStateLiveData.value = AddShoeState.VALID
        } else {
            _addShoeStateLiveData.value = AddShoeState.INVALID
        }
    }

    private fun isValidShoe(shoe: Shoe): Boolean {
        return shoe.name.isNotEmpty() && shoe.size.isNotEmpty() && shoe.company.isNotEmpty() && shoe.description.isNotEmpty()
    }

    private fun addShoe(shoe: Shoe) {
        _shoeListLiveData.value = _shoeListLiveData.value?.plus(shoe)?: listOf(shoe)
    }

    private fun addShoe(name: String, size: String, company: String, description: String) {
        val shoe = Shoe(name, size, company, description)
        addShoe(shoe)
    }

    fun updateState(state: AddShoeState) {
        _addShoeStateLiveData.value = state
    }

    fun createDefaultShoe(): Shoe = Shoe("", "", "", "")
}
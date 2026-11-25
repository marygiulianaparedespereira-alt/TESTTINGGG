
package etec.backend.service

data class Item(
    val nombre: String,
    val precio: Int
)

class OrdenDeCompraService {

    private val items = mutableListOf<Item>()

    fun agregarItem(item: Item) {
        items.add(item)
    }

    fun calcularTotal(): Int {
        return items.sumOf { it.precio }
    }
}

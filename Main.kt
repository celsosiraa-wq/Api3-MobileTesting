import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

fun main() {
    val apiUrl = "https://jsonkeeper.com/b/MX0A"
    var connection: HttpURLConnection? = null

    try {
        // Nos conectamos a la API para solicitar los productos.
        connection = URL(apiUrl).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = 10000
        connection.readTimeout = 10000

        val responseCode = connection.responseCode

        if (responseCode in 200..299) {
            // Leemos la respuesta que llega en formato JSON.
            val responseText = connection.inputStream
                .bufferedReader(Charsets.UTF_8)
                .use { it.readText() }

            val json = JSONObject(responseText)
            val products = json.getJSONArray("products")

            println("LISTADO DE PRODUCTOS")
            println("--------------------")

            // Mostramos el nombre y el precio de cada producto.
            for (i in 0 until products.length()) {
                val product = products.getJSONObject(i)
                val name = product.optString("name", "Nombre no disponible")
                val price = product.optDouble("price", 0.0)
                val currency = product.optString("currency", "")

                println("$name - $currency $price")
            }
        } else {
            println("No se pudieron obtener los productos. Código: $responseCode")
        }
    } catch (e: Exception) {
        println("Ocurrió un error al consultar la API: ${e.message}")
    } finally {
        connection?.disconnect()
    }
}

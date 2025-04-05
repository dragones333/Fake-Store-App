package com.example.fakestoreapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.border
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.models.Rating
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ProductCard(
    product: Product,
    onAguilasClick: (Product) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color(0xFFE3F2FD))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onAguilasClick(product) }
                .background(Color(0xFFFFA726), shape = RoundedCornerShape(16.dp))
                .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                .defaultMinSize(minHeight = 180.dp)
                .padding(12.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .background(Color(0xFF1E3A8A), shape = CircleShape)
                        .padding(8.dp)
                ) {
                    AsyncImage(
                        model = product.image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .background(Color(0xFF3B82F6), shape = RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = product.computedTitle,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Black),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    val testProduct = Product(
        id = 1,
        title = "iPhone 16",
        description = "Un buen telefon",
        price = 17_000.0,
        category = "Telefonica",
        image = "https://example.com/iphone.jpg",
        rating = Rating(count = 5, rate = 4.5)
    )
    FakeStoreAppTheme {
        ProductCard(product = testProduct, onAguilasClick = { println("Producto seleccionado: ${it.title}") })
    }
}

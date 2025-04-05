package com.example.fakestoreapp.screems

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.models.Rating
import com.example.fakestoreapp.services.ProductService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ProductDetailScreen(
    innerPadding: PaddingValues,
    productId: Int
) {
    val scope = rememberCoroutineScope()
    val BASE_URL = "https://fakestoreapi.com/"
    var product by remember {
        mutableStateOf(
            Product(
                id = 0,
                description = "",
                title = "",
                image = "",
                price = 0.0,
                category = "",
                rating = Rating(rate = 0.0, count = 0)
            )
        )
    }

    LaunchedEffect(true) {
        scope.launch {
            try {
                val retrofitBuilder = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val productService = retrofitBuilder.create(ProductService::class.java)

                val response = productService.getProductById(productId)
                product = response
                Log.i("ProductDetail", response.toString())
            } catch (e: Exception) {
                Log.e("ProductDetail", "Error fetching product details", e)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = product.title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 2.dp)
                )

                Text(
                    text = product.category,
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = product.description,
                    fontSize = 16.sp,
                    color = Color(0xFF5F6368),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = "$${product.price}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = "⭐ ${product.rating.rate} (${product.rating.count})",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

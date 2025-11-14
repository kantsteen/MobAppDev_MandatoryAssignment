package com.example.mobappdev_mandatoryassignment.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobappdev_mandatoryassignment.NavRoutes
import com.example.mobappdev_mandatoryassignment.model.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel? = null,
    onLoginClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    showProfileButton: Boolean = true,
) {
    val isLoggedIn = authViewModel?.isLoggedIn/*?.value ?: false*/

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = { Text(title) },
        actions = {
            // Show profile button only if logged in and showProfileButton is true
            if (isLoggedIn == true && showProfileButton) {
                Button(onClick = onProfileClick) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile"
                    )
                }
            }
            if (isLoggedIn == true){
                Button(onClick = {
                    authViewModel?.signOut()
                    onLogoutClick}) {Text("Logout") }
            }
            else {
                TextButton(onClick = onLoginClick) {Text("Login") }
            }


            // Show login/logout button
            /*if (isLoggedIn) {
                Button(onClick = {
                    authViewModel?.signOut()
                    onLogoutClick()
                }) {
                    Text("Logout")
                }
            } else {
                Button(onClick = {
                    onLoginClick()
                }) {
                    Text("Login")
                }
            }*/
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTopBar(
        title = "Sales Items"
    )
}

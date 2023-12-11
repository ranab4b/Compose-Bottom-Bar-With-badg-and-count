package com.example.composebottombarmenuwithbadgandcount

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebottombarmenuwithbadgandcount.ui.theme.ComposeBottomBarMenuWithBadgAndCountTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationComposable() {
    NavigationBar {
        var selectedIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        bottomNavigationItems.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(selected = selectedIndex == index, onClick = {
                selectedIndex = index
            }, icon = {
                BadgedBox(badge = {

                    if (bottomNavigationItem.badgeCount != null) {
                        Badge {
                            Text(text = "${bottomNavigationItem.badgeCount}")
                        }
                    } else if (bottomNavigationItem.hasNews) {
                        Badge()
                    }

                }) {
                    Icon(
                        imageVector = if (selectedIndex == index) {
                            bottomNavigationItem.iconEnabled
                        } else {
                            bottomNavigationItem.iconDisabled
                        }, contentDescription = bottomNavigationItem.title
                    )
                }

            }, label = { Text(text = bottomNavigationItem.title) }
            )
        }
    }
}

data class BottomNavigationItem(
    val iconEnabled: ImageVector,
    val iconDisabled: ImageVector,
    val title: String,
    val hasNews : Boolean = false,
    val badgeCount : Int? = null
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        iconEnabled = Icons.Filled.Home,
        iconDisabled = Icons.Outlined.Home,
        title = "home"
    ),
    BottomNavigationItem(
        iconEnabled = Icons.Filled.Email,
        iconDisabled = Icons.Outlined.Email,
        title = "email",
        badgeCount = 11
    ),
    BottomNavigationItem(
        iconEnabled = Icons.Filled.Settings,
        iconDisabled = Icons.Outlined.Settings,
        title = "settings",
        hasNews = true
    )
)


@Preview(showBackground = true)
@Composable
fun BottomNavigationComposablePreview() {
    ComposeBottomBarMenuWithBadgAndCountTheme {
        BottomNavigationComposable()
    }
}




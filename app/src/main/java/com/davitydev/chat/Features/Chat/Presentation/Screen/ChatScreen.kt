import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "User Icon",
                            modifier = Modifier.size(40.dp)
                        )
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text("Somer", style = MaterialTheme.typography.titleMedium)
                            Text(
                                "Estudiante",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Reply,
                        contentDescription = "Reply",
                        modifier = Modifier.padding(end = 16.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Escribir mensaje") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                Message(
                    text = "Hola david, pásame la tarea del profe ali no la hice se me olvido hacerla, estaba trabajando y no me dio tiempo",
                    isFromMe = false
                )
            }
            item { Message(text = "ok no te preocupes bro", isFromMe = true) }
            item {
                Message(
                    text = "Hay que hacer equipo para lo del proyecto que dejo el profe sirgei",
                    isFromMe = false
                )
            }
            item { Message(text = "Va", isFromMe = true) }
        }
    }
}

@Composable
fun Message(text: String, isFromMe: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isFromMe) Arrangement.End else Arrangement.Start
    ) {
        if (!isFromMe) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Sender Icon",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Bottom),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = if (isFromMe) Color(0xFFE7E7E7) else Color(0xFFE0F7FA)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        if (isFromMe) {
            Spacer(modifier = Modifier.size(8.dp))
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "My Icon",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Bottom),
                tint = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}
package dev.giussepr.jetpackcompose.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = value,
        modifier = modifier
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.titleMedium,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.DecorationBox(
                contentPadding = PaddingValues(0.dp),
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        placeholder,
                        color = MaterialTheme.typography.titleMedium.color.copy(
                            alpha = 0.5f
                        )
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    )
}
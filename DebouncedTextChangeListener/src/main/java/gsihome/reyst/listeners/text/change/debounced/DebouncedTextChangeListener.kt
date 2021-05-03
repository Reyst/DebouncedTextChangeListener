package gsihome.reyst.listeners.text.change.debounced

import android.text.Editable
import android.text.TextWatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class DebouncedTextChangeListener(
    scope: CoroutineScope,
    timeout: Long = 500L,
    onChangeAction: (String) -> Unit,
) : TextWatcher {

    private val state = MutableStateFlow("")

    init {

        scope.launch {
            state.asStateFlow()
                .debounce(timeout)
                .collect { str ->
                    timeout.takeIf { it > 0 }?.also { delay(it) }
                    onChangeAction(str)
                }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun afterTextChanged(s: Editable?) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        state.tryEmit(s?.toString().orEmpty())
    }
}
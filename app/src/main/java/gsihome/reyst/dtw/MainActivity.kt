package gsihome.reyst.dtw

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import gsihome.reyst.listeners.text.change.debounced.DebouncedTextChangeListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edit = findViewById<EditText>(R.id.edit)

        edit.addTextChangedListener(
            DebouncedTextChangeListener(lifecycleScope) { Log.wtf("INSPECT", "str = '$it'") }
        )
    }
}
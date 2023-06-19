package mx.edu.itch.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.itch.roomdatabase.ui.main.MainFragment
import mx.edu.itch.roomdatabase.ui.order.main.MainFragmentOrder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
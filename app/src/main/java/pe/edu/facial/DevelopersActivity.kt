package pe.edu.facial

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DevelopersActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developers)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "DEVELOPERS"

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()

            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_facts -> {
                    val intent = Intent(this, FactsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_developers -> {
                    // Already in DevelopersActivity
                }
                R.id.nav_about -> {
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    fun openGitHub(view: View) {
        val url = when (view.id) {
            R.id.developer1_github -> "https://github.com/developer1"
            R.id.developer2_github -> "https://github.com/developer2"
            else -> ""
        }
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    fun openLinkedIn(view: View) {
        val url = when (view.id) {
            R.id.developer1_linkedin -> "https://www.linkedin.com/in/developer1"
            R.id.developer2_linkedin -> "https://www.linkedin.com/in/developer2"
            else -> ""
        }
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    fun openEmail(view: View) {
        val email = when (view.id) {
            R.id.developer1_email -> "developer1@example.com"
            R.id.developer2_email -> "developer2@example.com"
            else -> ""
        }
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
        }
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}

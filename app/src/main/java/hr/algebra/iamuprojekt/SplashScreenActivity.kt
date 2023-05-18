package hr.algebra.iamuprojekt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.iamuprojekt.databinding.ActivitySplashScreenBinding
import hr.algebra.iamuprojekt.framework.*

private const val DELAY = 3000L
const val DATA_IMPORTED = "hr.algebra.iamuprojekt.data_imported"

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun startAnimations() {
        binding.tvSplash.applyAnimation(R.anim.blink)
        binding.ivSplash.applyAnimation(R.anim.rotate)
    }

    private fun redirect() {
        if (getBooleanPreference(DATA_IMPORTED)) {
            callDelayed(DELAY) { startActivity<HostActivity>() }
        } else {
            if (isOnline()) {
                IamuService.enqueue(this)
            } else {
                binding.tvSplash.text = getString(R.string.no_internet)
                callDelayed(DELAY) { finish() }
            }
        }
    }


}
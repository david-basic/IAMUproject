package hr.algebra.iamuprojekt

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.iamuprojekt.framework.setBooleanPreference
import hr.algebra.iamuprojekt.framework.startActivity

class IamuReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED)
        context.startActivity<HostActivity>()
    }

}
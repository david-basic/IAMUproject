package hr.algebra.iamuprojekt

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.iamuprojekt.api.NasaFetcher
import hr.algebra.iamuprojekt.framework.sendBroadcast

private const val JOB_ID = 1

@Suppress("DEPRECATION")
class IamuService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        //Thread.sleep(6000)
        NasaFetcher(this).fetchItems(10)
    }

    companion object {
        fun enqueue(context: Context) {
            enqueueWork(
                context,
                IamuService::class.java,
                JOB_ID,
                Intent(context, IamuService::class.java)
            )
        }
    }
}
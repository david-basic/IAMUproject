package hr.algebra.iamuprojekt.dao.factory

import android.content.Context
import hr.algebra.iamuprojekt.dao.NasaSqlHelper

fun getNasaRepository(context: Context?) = NasaSqlHelper(context)
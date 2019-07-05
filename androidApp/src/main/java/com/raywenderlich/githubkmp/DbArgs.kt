import android.content.Context
import com.raywenderlich.Db
import com.raywenderlich.MyDatabase
import com.raywenderlich.MyDatabase.Companion.Schema
import com.squareup.sqldelight.android.AndroidSqliteDriver

fun Db.getInstance(ctx: Context): MyDatabase {
    if (!ready) {
        dbSetup(AndroidSqliteDriver(Schema, ctx))
    }

    return instance
}

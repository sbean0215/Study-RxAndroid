package bean.study.rx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val publishEvent = Observable.create<List<String>> {
            findViewById<Button>(R.id.button).setOnClickListener{ v ->
                Log.d("BEAN", "버튼 1 클릭")
                it.onNext(listOf<String>("1","2", "3", "4", "5"))
            }

            findViewById<Button>(R.id.button2).setOnClickListener{ v ->
                Log.d("BEAN", "버튼 2 클릭")
                it.onNext(listOf<String>("일","이", "삼", "사", "오"))
            }
        }

        publishEvent.subscribeOn(Schedulers.io())
//            .skip(2)
            .doOnNext{ event -> Log.d("BEAN", "이벤트 발생!! " + event.toString()) }
            .switchMap { list ->
                Observable.create<String> {
  //                  it.onNext()
                }
            }
//            .switchMap {  }


    }
}

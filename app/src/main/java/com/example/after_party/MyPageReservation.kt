import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.after_party.databinding.ActivityMyPageReservationBinding

class MyPageReservation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMyPageReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 예약 목록 데이터
        val reservationData = arrayOf("예약 1", "예약 2", "예약 3", "예약 4", "예약 5")

        // 어댑터 생성 및 ListView에 연결
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, reservationData)
        binding.reservationListView.adapter = adapter
    }
}

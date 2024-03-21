Launched Effect
=====================

Bildiğimiz gibi Compose fonksiyon içerisinde herhangi bir değişiklik sonrası fonksiyon içerisindeki tüm değerler yeniden oluşturularak ekran çizdirilir. Yaptığımız değişikliklerin bazı fonksiyonları etkilememesini isteriz. Launch Effect de bu gereksinimi bizlere karşılar. Örneğin sunucuya istekte bulunan bir coroutine yapısı her ekran çizdirilmesinden etkilenmemelidir.

Senaryo
------------

Uygulamamızda First Text clicked ve Second Text clicked adında iki tıklanabilir text yer almakta. Bu textlere tıkladığımızda state'leri güncellenir, güncellenen stateler sayesinde ekran yeniden çizdilir.

**Durum 1-)** Toast mesajını LaunchEffect bloğunun dışına yazıp LaunchEffect fonksiyonunu yorum satırları içerisine aldığımızda her güncellenen stateler sayesinde ekrana yeni bir Toast mesajı yazdırılır.

**Durum 2-)** Toast mesajını LaunchEffect bloğunun içerisine taşıyıp LaunchEffect fonksiyonuna Unit değerini verdiğimizde state değişikliğinden etkilenmeyip Toast mesajı ekrana basılmaz.

**Durum 3-)** Toast mesajını LaunchEffect bloğunun içerisine taşıyıp LaunchEffect fonksiyonuna stateFirst değerini verdiğimizde sadece stateFirst değişikliğinden etkilenerek ekrana Toast mesajını basarız yani diğer state değişikliğinden etkilenmeyiz.








Side Effect
=====================

- Yan etkinin yalnızca geçerli Composable fonksiyonu yeniden oluşturulduğunda tetiklendiğini ve iç içe geçmiş Composable fonksiyonları için tetiklenmediğini unutmayın. Bu, başka bir Composable fonksiyonunu çağıran bir Composable fonksiyonunuz varsa, iç Composable fonksiyonu yeniden oluşturulduğunda dış Composable fonksiyonundaki SideEffect'in tetiklenmeyeceği anlamına gelir.

- Yani sideEffect bir bakıma kapsamlandırma işlemi de sağlar. İlk olarak yürütüldükten sonra doğrudan etkileşmli bir composable tetiklemez ise sideEffect çalışmaz.

 - Compose state'ini compose tarafından yönetilmeyen nesnelerle paylaşmak için SideEffect composable kullanın. Bir SideEffect kullanmak, efektin her başarılı recompositiondan sonra yürütülmesini garanti eder.

- Compose olmayan herhangi bir değer değiştirilirse, yeniden birleştirme gerçekleşmeyecektir.

- fetchData() ağ çağrısı kullanıcı tarafından birden çok kez çağrılabilir, bu da bir seferde yüksek miktarda istek alarak sunucunun aşırı yüklenmesine neden olur

- fetchData() ağ çağrısının yanıt vermesi veya sunucudan uygulamaya veri göndermesi daha uzun sürebilir

- Bu sorunları çözmek için SideEffect, parent composable'ın recomposition olması API çağrıları, korutin yürütme vb. gibi asenkron işlemleri yürütür.



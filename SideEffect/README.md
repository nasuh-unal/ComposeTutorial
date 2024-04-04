Side Effect
=====================

- Yan etkinin yalnızca geçerli Composable fonksiyonu yeniden oluşturulduğunda tetiklendiğini ve iç içe geçmiş Composable fonksiyonları için tetiklenmediğini unutmayın. Bu, başka bir Composable fonksiyonunu çağıran bir Composable fonksiyonunuz varsa, iç Composable fonksiyonu yeniden oluşturulduğunda dış Composable fonksiyonundaki SideEffect'in tetiklenmeyeceği anlamına gelir.

- Yani sideEffect bir bakıma kapsamlandırma işlemi de sağlar. İlk olarak yürütüldükten sonra doğrudan etkileşmli bir composable tetiklemez ise sideEffect çalışmaz.

 - Compose state'ini compose tarafından yönetilmeyen nesnelerle paylaşmak için SideEffect composable kullanın. Bir SideEffect kullanmak, efektin her başarılı recompositiondan sonra yürütülmesini garanti eder.


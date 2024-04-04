Disposable Effect
=====================

Disposable effect ilk çalıştırıldığında bir işlemi başlatırız fakat composable UI hiyerarşisinden kaldırıldığında onDispose tetiklenerek işlemlerimizi durdurabiliriz.Bir DisposableEffect, genellikle bir Composable fonksiyon içinde kullanılır ve genellikle kaynak yönetimi için kullanılır.

Effect Block: Bu, DisposableEffect içindeki işlemlerin tanımlandığı bloktur. Genellikle bir kaynağı başlatmak veya başka bir etkileşim başlatmak için kullanılır.

OnDispose Bloğu: Bu blok, DisposableEffect kapsamından çıkıldığında çalıştırılacak olan kodu içerir. Bu blokta, kaynakların temizlenmesi veya kapatılması gibi işlemler gerçekleştirilir.

onDsipose composable UI hiyerarşisinden kaldırıldığıında tetiklenir bu durum kaynakları temizlememizi sağlar.

Sayfa değişikliğinde onDispose tetiklenir.

Uygulama ilk ayağa kalktığında DisposableEffect tetiklenir fakat onDispose çalışmaz

DisposableEffect parametresi değiştiğinde ilk olarak onDispose çalışır ardından DisposableEffect çalışır.

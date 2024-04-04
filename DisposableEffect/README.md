Remember Coroutine Scope
=====================

Remember coroutine scope async işlemleri yürütmemiz için kullanılan bir scope'dur. Compose fonksiyonlar oluşabilecek değişikliklerde hemen kendilerini yeniden çizerler bu yüzden yaşam döngülerini yönetebiliriz.

- Kodları bloklamaz async şekilde yürütür.
- Scope içerisinde oluşabilecek değişiklikler compose'u yeniden çizmez.
- Scope içerisinde diğer state'leri etkilerse UI yeniden çizdirilir.
- Dışarıdan bir coroutine alabiliriz.
- Coroutine'i tetiklememiz sonucu çağırmak istiyorsak LaunchEffect yerine Remember Coroutine Scope kullanmalıyız.

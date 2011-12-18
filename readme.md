### Hibernate 101 ###
This is a sample hibernate project that show you various use case with 
hibernate.


## Branch ##
# race-condition #
Show a race-condition when trying to 
- SessionFactory#openSession() and
- EntityManagerFactory#createEntityManager()
at the same time. This is only happen in test only, because 
SessionFactory#openSession() and EntityManagerFactory#createEntityManager() 
calling in the same @Before method.
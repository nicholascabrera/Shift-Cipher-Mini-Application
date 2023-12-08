# Shift Cipher Mini Application
This Mini Application is a small project I am working on which encrypts and decrypts simple shift ciphers given some shift amount.

## Features
The mini application boasts a few features users might find appealing:
1. Simple GUI with Java Swing
2. Smart Event Handling
3. Efficient Encryption and Decryption

## Next Steps
This application is not currently capable of breaking an encrypted shift cipher without user assistance, in that it can not ascertain the correct shift amount on its own, but the user can cycle the shift amount until they get a plaintext string that looks like valid language. This can be rectified by using a database that contains valid words and checking each shift amount against this database. Due to the nature of the shift cipher, it would still be an efficient algorithm, as, at worst, the algorithm would only need to be run 26 times.

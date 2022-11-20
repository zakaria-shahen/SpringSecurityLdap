### Applying Spring Security LDAP

Applying Spring Security LDAP Knowledge and a Quick Review of Security Strategy (threads).

- use ldap to Authentication
- use Security Strategy - threads
  - use DelegatingSecurityContextCallable 
- use Gradle
- Spring Data Jpa: @IdClass (Composite Key)

#### commits

```shell

* 767226a (HEAD -> main) move security context to other thread not mangament by spring uing DelegatingSecurityContextCallable or DelegatingSecurityContextRunnable
* 3583565 Set Security Strategy to  MODE_INHERITABLETHREADLOCAL
* fe7dc45 ldap authorities code
* 7b25352 switch to ldap without Authorities
* 578b3cf switch to spring data
* b7221ad switch to jdbc and mysql
* d5716b0 add security configuration
* 020cd5e add controllers
* cf518b4 initialization


```
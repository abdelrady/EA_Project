package edu.mum.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import edu.mum.domain.User;

@Aspect
@Component
public class AuctionAspect {

	// 1 implcit pointcut
	/*
	 * @Before("execution(* edu.mum.service..*(..))") public void
	 * logMethodNames(JoinPoint joinPoint) { System.out.println("TARGET CLASS : " +
	 * joinPoint.getSignature().getDeclaringTypeName() + "." +
	 * joinPoint.getSignature().getName()); }
	 */

	// 2 explicit pointcut
	/*
	 * @Pointcut("execution(* edu.mum.service..*(..))") public void logMethodNames()
	 * {
	 * 
	 * }
	 * 
	 * @After("logMethodNames()") public void logMethodNamesExplicit(JoinPoint
	 * joinPoint) { System.out.println("TARGET CLASS : " +
	 * joinPoint.getSignature().getDeclaringTypeName() + "." +
	 * joinPoint.getSignature().getName()); }
	 */

	// 3 explicit pointcut - change .. into Long
	/*
	 * @Pointcut("execution(* edu.mum.service..*(Long))") public void
	 * logMethodNames() {
	 * 
	 * }
	 * 
	 * @After("logMethodNames()") public void logMethodNamesExplicit(JoinPoint
	 * joinPoint) { System.out.println("TARGET CLASS : " +
	 * joinPoint.getSignature().getDeclaringTypeName() + "." +
	 * joinPoint.getSignature().getName()); }
	 */

	// 4,5 explicit pointcut - change Long to ()
	/*
	 * @Pointcut("execution(* edu.mum.service..*())") public void logMethodNames() {
	 * 
	 * }
	 * 
	 * @After("logMethodNames()") public void logMethodNamesExplicit(JoinPoint
	 * joinPoint) { System.out.println("TARGET CLASS : " +
	 * joinPoint.getSignature().getDeclaringTypeName() + "." +
	 * joinPoint.getSignature().getName()); }
	 */

	// 6 explicit pointcut - with a combination of a within() and an args()
	/*@Pointcut("within(edu.mum.service..*) && args(Long)")
	public void logMethodNames() {

	}

	@After("logMethodNames()")
	public void logMethodNamesExplicit(JoinPoint joinPoint) {
		System.out.println("TARGET CLASS : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
	}*/

	//7
	@Pointcut("within(edu.mum.service..*)")
	public void logMethodNames() {

	}

	@After("logMethodNames() && args(user)")
	public void logResourceName(JoinPoint joinPoint, User user) {
		System.out.println("TARGET CLASS : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName() + ", User Name: " + user.getFirstName() + " " + user.getLastName());
	}
	
	// 6-Replace the execution pointcut with a combination of a within() and an
	// args() pointcut
	// @Before("within(edu.mum.service..*)")
	// @Before("within(edu.mum.service..*) && args(Long)")
	// @Pointcut("execution(* edu.mum.service..*(..))")
	// public void logMethodNames(JoinPoint joinPoint) {

	// }

	// 2-Make the pointcut into an explicit pointcut.
	// 3-Change the pointcut arguments from (..) to (Long)
	// 4-Change the pointcut arguments from (Long) to ()
	// 5-Add a findAll() to testItems.java

	/*
	 * @Before("applicationMethod()") public void logAllServiceMethods(JoinPoint
	 * joinPoint) { System.out.println("   **********     TARGET CLASS : " +
	 * joinPoint.getSignature().getDeclaringTypeName() + "." +
	 * joinPoint.getSignature().getName() + "    **********"); }
	 */

	// 7-Add another Advice method with a User parameter:
	// @Before("within(edu.mum.service..*) && args(user)")
	/*
	 * public void logResourceName(JoinPoint joinPoint, User user) {
	 * System.out.println( "   **********     TARGET CLASS : " +
	 * joinPoint.getSignature().getDeclaringTypeName() + "." +
	 * joinPoint.getSignature().getName() + "    **********" + "User First Name: " +
	 * user.getFirstName()); }
	 */

}

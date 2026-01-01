# Complex Record Definitions - Quick Reference

This document provides examples and patterns for creating complex record structures in EBS2.

## Table of Contents

1. [Basic Record Types](#basic-record-types)
2. [Record Field Attributes](#record-field-attributes)
3. [Record Inheritance (Extension)](#record-inheritance-extension)
4. [Nested Records](#nested-records)
5. [Records with Arrays](#records-with-arrays)
6. [Arrays of Records](#arrays-of-records)
7. [Records with All Data Types](#records-with-all-data-types)
8. [Multi-Level Inheritance](#multi-level-inheritance)
9. [Deep Nesting Patterns](#deep-nesting-patterns)
10. [Practical Examples](#practical-examples)

---

## Basic Record Types

### Simple Record Definition

```javascript
record type PersonType
    name as text
    age as number
    email as text
end type
```

### Record with Type Suffix Convention

Always use `Type` suffix for record type names:

```javascript
record type EmployeeType
    employeeId as number
    department as text
    salary as number
end type
```

### Anonymous Records

```javascript
// Without type definition
var person = record {
    name: "Alice",
    age: 30,
    city: "Boston"
}
```

---

## Record Field Attributes

### Mandatory Fields

Fields marked as `mandatory` must be provided when creating a record instance:

```javascript
record type UserType
    username as text mandatory
    email as text mandatory
    age as number
end type

// Valid: all mandatory fields provided
var user as UserType = record {
    username: "alice",
    email: "alice@example.com",
    age: 25
}

// Invalid: missing mandatory field
// var invalid as UserType = record {
//     username: "bob"
//     // ERROR: email is mandatory
// }
```

### Default Values

Fields with `default:value` get the specified value if not provided:

```javascript
record type ProductType
    name as text mandatory
    price as number default:0.00
    inStock as number default:0
    category as text default:"Uncategorized"
end type

var product as ProductType = record {
    name: "Widget"
    // price: 0.00 (default)
    // inStock: 0 (default)
    // category: "Uncategorized" (default)
}
```

### Maximum Length Constraints

Use `maxlength:n` to limit text field length:

```javascript
record type CommentType
    username as text mandatory maxlength:50
    text as text mandatory maxlength:500
    email as text maxlength:100
end type

var comment as CommentType = record {
    username: "alice",
    text: "Great article!",
    email: "alice@example.com"
}

// Validation occurs at assignment
comment.text = "Short comment"  // OK
// comment.text = "Very long..."  // ERROR: exceeds maxlength:500
```

### Combining Attributes

Multiple attributes can be used together:

```javascript
record type PersonType
    name as text mandatory maxlength:100
    email as text mandatory maxlength:100
    phone as text maxlength:20
    age as number default:0
    bio as text maxlength:2000
    isActive as flag default:true
end type

var person as PersonType = record {
    name: "Charlie",
    email: "charlie@example.com"
    // phone: not provided (optional)
    // age: 0 (default)
    // bio: not provided (optional)
    // isActive: true (default)
}
```

### Attributes with Nested Records

Field attributes work with nested records:

```javascript
record type AddressType
    street as text mandatory maxlength:200
    city as text mandatory maxlength:100
    state as text mandatory maxlength:50
    zipCode as text mandatory maxlength:10
    country as text default:"USA" maxlength:100
end type

// Alternative syntax with curly braces (both forms are equivalent)
record type EmployeeType {
    name as text mandatory maxlength:100
    email as text mandatory maxlength:100
    address as AddressType mandatory
    department as text default:"Unassigned"
    salary as number default:0
}

var employee as EmployeeType = record {
    name: "David",
    email: "david@company.com",
    address: record {
        street: "456 Oak Ave",
        city: "Boston",
        state: "MA",
        zipCode: "02101"
        // country: "USA" (default)
    }
    // department: "Unassigned" (default)
    // salary: 0 (default)
}
```

**Benefits:**
- ✅ **Required fields** - Enforce mandatory data with `mandatory`
- ✅ **Default values** - Reduce boilerplate with sensible defaults
- ✅ **Length validation** - Prevent data overflow with `maxlength:n`
- ✅ **Self-documenting** - Constraints visible in type definition
- ✅ **Runtime checks** - Violations caught immediately

---

## Record Inheritance (Extension)

### Single-Level Extension

```javascript
record type PersonType
    name as text
    age as number
end type

record type EmployeeType extends PersonType
    employeeId as number
    department as text
    salary as number
end type

// Employee has: name, age, employeeId, department, salary
```

### Multi-Level Extension

```javascript
record type PersonType
    name as text
    age as number
end type

record type EmployeeType extends PersonType
    employeeId as number
    department as text
end type

record type ManagerType extends EmployeeType
    teamSize as number
    budget as number
end type

record type ExecutiveType extends ManagerType
    title as text
    stockOptions as number
end type

// Executive has all fields from Person, Employee, Manager, plus its own
```

---

## Nested Records

### Simple Nesting

```javascript
record type AddressType
    street as text
    city as text
    state as text
    zipCode as text
end type

record type PersonType
    name as text
    address as AddressType
end type

// Create instance
var person as PersonType = record {
    name: "Alice",
    address: record {
        street: "123 Main St",
        city: "Boston",
        state: "MA",
        zipCode: "02101"
    }
}

// Access nested field
print person.address.city  // "Boston"
```

### Multi-Level Nesting

```javascript
record type ContactType
    name as text
    phone as text
    email as text
end type

record type AddressType
    street as text
    city as text
    emergencyContact as ContactType
end type

record type EmployeeType
    name as text
    homeAddress as AddressType
    workAddress as AddressType
end type

// Access deeply nested field
print employee.homeAddress.emergencyContact.phone
```

---

## Records with Arrays

### Arrays of Primitive Types

```javascript
record type CourseType
    title as text
    instructor as text
    students as array.text
    grades as array.number
end type

var course as CourseType = record {
    title: "Math 101",
    instructor: "Prof. Smith",
    students: {"Alice", "Bob", "Charlie"},
    grades: {95, 87, 92}
}

// Access array elements
print course.students[0]  // "Alice"
print course.grades[1]    // 87
```

### Arrays with Type Constraints

```javascript
record type TeamType
    name as text
    members as array.text
    scores as array.number
    status as array.indicator("active", "inactive", "pending")
end type
```

---

## Arrays of Records

### Array of Named Record Types

```javascript
record type StudentType
    name as text
    grade as number
end type

record type ClassroomType
    roomNumber as text
    students as array.record(StudentType)
end type

var classroom as ClassroomType = record {
    roomNumber: "101",
    students: {
        record { name: "Alice", grade: 95 },
        record { name: "Bob", grade: 87 },
        record { name: "Charlie", grade: 92 }
    }
}

// Access records in array
print classroom.students[0].name  // "Alice"
print classroom.students[1].grade // 87
```

### Array of Anonymous Records

```javascript
var team = record {
    name: "Tigers",
    members: {
        record { name: "Alice", position: "Forward" },
        record { name: "Bob", position: "Guard" },
        record { name: "Charlie", position: "Center" }
    }
}
```

---

## Records with All Data Types

### Comprehensive Example

```javascript
record type EmployeeType
    // Text fields
    firstName as text
    lastName as text
    email as text
    
    // Number fields
    employeeId as number
    salary as number
    hoursPerWeek as number
    
    // Flag fields
    isFullTime as flag
    hasInsurance as flag
    isRemote as flag
    
    // Date fields
    hireDate as date
    birthDate as date
    lastReview as date
    
    // Indicator fields
    status as indicator ("active", "on-leave", "terminated") = "active"
    department as indicator ("Engineering", "Sales", "HR", "Finance") = "Engineering"
    
    // Number with range constraint
    performanceRating as number 0.0..5.0
    vacationDays as number 0..30
    
    // Array fields
    skills as array.text
    certifications as array.text
    projectIds as array.number
    
    // Nested record
    address as AddressType
    emergencyContact as PersonType
end type
```

---

## Multi-Level Inheritance

### 4-Level Hierarchy Example

```javascript
// Level 1: Base
record type PersonType
    firstName as text
    lastName as text
    dateOfBirth as date
    email as text
end type

// Level 2: Extends Person
record type EmployeeType extends PersonType
    employeeId as number
    department as text
    hireDate as date
    salary as number
end type

// Level 3: Extends Employee
record type ManagerType extends EmployeeType
    teamSize as number
    budget as number
    directReports as array.record
end type

// Level 4: Extends Manager
record type ExecutiveType extends ManagerType
    title as text
    stockOptions as number
    boardMember as flag
    executiveLevel as indicator ("VP", "SVP", "CXO", "CEO") = "VP"
end type

// Type checking works at all levels
var exec as ExecutiveType = record { /* ... */ }

if exec typeof ExecutiveType then
    print "Is Executive"
end if

if exec typeof ManagerType then
    print "Is Manager (inherited)"
end if

if exec typeof EmployeeType then
    print "Is Employee (inherited)"
end if

if exec typeof PersonType then
    print "Is Person (inherited)"
end if
```

---

## Deep Nesting Patterns

### 5+ Levels Deep

```javascript
record type ContactType
    name as text
    phone as text
end type

record type AddressType
    street as text
    city as text
    emergencyContact as ContactType
end type

record type EmployeeType
    name as text
    address as AddressType
end type

record type ProjectType
    name as text
    lead as EmployeeType
end type

record type DepartmentType
    name as text
    projects as array.record
end type

record type CompanyType
    name as text
    departments as array.record
end type

// Create instance
var company as CompanyType = record {
    name: "TechCorp",
    departments: {
        record {
            name: "Engineering",
            projects: {
                record {
                    name: "Project Alpha",
                    lead: record {
                        name: "Alice",
                        address: record {
                            street: "123 Main St",
                            city: "Boston",
                            emergencyContact: record {
                                name: "Bob",
                                phone: "555-0100"
                            }
                        }
                    }
                }
            }
        }
    }
}

// Access deeply nested field (5 levels)
var emergencyPhone = company.departments[0].projects[0].lead.address.emergencyContact.phone
print emergencyPhone  // "555-0100"
```

---

## Practical Examples

### Example 1: E-Commerce System

```javascript
record type ProductType
    productId as number
    name as text
    description as text
    price as number
    inStock as number
    category as text
    tags as array.text
end type

record type OrderItemType
    product as ProductType
    quantity as number
    price as number
end type

record type OrderType
    orderId as number
    customer as PersonType
    items as array.record(OrderItemType)
    totalAmount as number
    orderDate as date
    shippingAddress as AddressType
    status as indicator ("pending", "processing", "shipped", "delivered") = "pending"
end type

record type CustomerType extends PersonType
    customerId as number
    orders as array.record(OrderType)
    shippingAddresses as array.record(AddressType)
    preferredPayment as text
    loyaltyPoints as number
end type
```

### Example 2: School Management System

```javascript
record type StudentType extends PersonType
    studentId as number
    grade as number
    gpa as number 0.0..4.0
    courses as array.record
    attendance as number 0..100
end type

record type CourseType
    courseId as number
    title as text
    instructor as PersonType
    students as array.record(StudentType)
    credits as number
    schedule as text
end type

record type ClassroomType
    roomNumber as text
    building as text
    capacity as number
    courses as array.record(CourseType)
end type

record type SchoolType
    name as text
    address as AddressType
    principal as PersonType
    students as array.record(StudentType)
    classrooms as array.record(ClassroomType)
    founded as date
end type
```

### Example 3: Hospital System

```javascript
record type PatientType extends PersonType
    patientId as number
    bloodType as text
    allergies as array.text
    medicalHistory as array.text
    emergencyContacts as array.record(ContactType)
    insurance as text
end type

record type DoctorType extends PersonType
    doctorId as number
    specialty as text
    licenseNumber as text
    patients as array.record(PatientType)
    appointments as array.record
end type

record type AppointmentType
    appointmentId as number
    patient as PatientType
    doctor as DoctorType
    dateTime as date
    reason as text
    notes as text
    status as indicator ("scheduled", "completed", "cancelled") = "scheduled"
end type

record type HospitalType
    name as text
    address as AddressType
    doctors as array.record(DoctorType)
    patients as array.record(PatientType)
    appointments as array.record(AppointmentType)
end type
```

---

## Best Practices

### 1. Use Type Suffix Convention

Always use `Type` suffix for record type names:

```javascript
// Good
record type PersonType
record type EmployeeType
record type ManagerType

// Avoid
record type Person
record type Employee
record type Manager
```

### 2. Keep Inheritance Hierarchies Manageable

Limit to 3-4 levels of inheritance for maintainability:

```javascript
// Good: 4 levels
PersonType → EmployeeType → ManagerType → ExecutiveType

// Too deep: 6+ levels (avoid)
PersonType → EmployeeType → ManagerType → DirectorType → VPType → ExecutiveType
```

### 3. Use Nested Records for Related Data

Group related fields into their own record types:

```javascript
// Good
record type EmployeeType
    name as text
    address as AddressType
    contact as ContactType
end type

// Avoid flat structure
record type EmployeeType
    name as text
    street as text
    city as text
    state as text
    phone as text
    alternatePhone as text
end type
```

### 4. Use Indicators for Enumerations

Use indicator types instead of text for constrained values:

```javascript
// Good
record type EmployeeType
    status as indicator ("active", "on-leave", "terminated") = "active"
end type

// Less safe
record type EmployeeType
    status as text  // Could be any text value
end type
```

### 5. Use Range-Constrained Numbers

Use range constraints for numbers with known bounds:

```javascript
// Good
record type EmployeeType
    performanceRating as number 0.0..5.0
    vacationDays as number 0..30
    age as number 18..120
end type

// Less safe
record type EmployeeType
    performanceRating as number
    vacationDays as number
    age as number
end type
```

---

## Summary

Complex record definitions in EBS2 support:

- ✅ Multiple levels of inheritance (4+ levels)
- ✅ Deep nesting (5+ levels)
- ✅ Arrays of records
- ✅ All data types: text, number, flag, date, indicator
- ✅ Type checking with `typeof` operator
- ✅ Range-constrained numbers
- ✅ Type-safe indicators/enumerations
- ✅ Flexible anonymous records
- ✅ Real-world enterprise data modeling

For a complete working example, see:
- **doc/examples/07_records.ebs** - Basic record examples
- **doc/examples/10_complex_records.ebs** - Advanced enterprise-level example
- **doc/EBS2_LANGUAGE_SPEC.md** - Complete language specification

---

*EBS2 - Making complex data structures simple and type-safe.*

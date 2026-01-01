# What Does a Complex Record Definition Look Like?

This document answers the question: **"What does a complex record definition look like in EBS2?"**

## Quick Answer

A complex record definition in EBS2 can include:
- Multiple levels of inheritance (4+ levels)
- Deeply nested records (5+ levels deep)
- Arrays of records within records
- All data types (text, number, flag, date, indicator, arrays)
- Type constraints (ranges, enumerations)
- Field attributes (mandatory, default values, max length)
- Recursive structures

---

## Simple Example

Here's a basic record:

```javascript
record type PersonType
    name as text
    age as number
    email as text
end type
```

---

## Complex Example (Real-World Scenario)

Here's a **complex** record definition from an enterprise company management system:

```javascript
// Multi-level inheritance (4 levels deep)
record type PersonType
    firstName as text
    lastName as text
    dateOfBirth as date
    email as text
    phone as text
end type

record type EmployeeType extends PersonType
    employeeId as number
    department as text
    position as text
    hireDate as date
    salary as number
    status as indicator ("active", "on-leave", "terminated") = "active"
    address as AddressType                    // Nested record
    emergencyContact as PersonType            // Nested record of same base type
end type

record type ManagerType extends EmployeeType
    teamSize as number
    budget as number
    directReports as array.record             // Array of records
    performanceRating as number 0.0..5.0      // Constrained range
end type

record type ExecutiveType extends ManagerType
    title as text
    stockOptions as number
    boardMember as flag
    assistants as array.record                // Another array of records
    executiveLevel as indicator ("VP", "SVP", "CXO", "CEO") = "VP"
end type
```

---

## Even More Complex: Full Company Structure

A **very complex** nested structure with everything:

```javascript
record type CompanyType
    companyId as number
    name as text
    founded as date
    headquarters as OfficeType                      // Nested record (Office)
    offices as array.record                         // Array of Office records
    ceo as ExecutiveType                           // Nested record (4-level inheritance)
    executives as array.record                      // Array of Executive records
    departments as array.record                     // Array of Department records
    employees as array.record                       // Array of all Employee records
    totalRevenue as number
    fiscalYearEnd as date
    stockSymbol as text
    isPublic as flag
end type

record type DepartmentType
    departmentId as number
    name as text
    head as ManagerType                            // Manager (3-level inheritance)
    employees as array.record                       // Array of Employee records
    subdepartments as array.record                  // Recursive! Departments within departments
    budget as number
    location as AddressType                         // Nested Address record
    projects as array.record                        // Array of Project records
end type

record type ProjectType
    projectId as number
    name as text
    description as text
    manager as ManagerType                          // Manager (nested)
    teamMembers as array.record                     // Array of Employee records
    tasks as array.record                           // Array of Task records
    milestones as array.record                      // Array of Milestone records
    budget as number
    spent as number
    startDate as date
    endDate as date
    status as indicator ("planning", "active", "on-hold", "completed", "cancelled") = "planning"
end type

record type TaskType
    taskId as number
    title as text
    description as text
    assignedTo as EmployeeType                      // Nested Employee
    priority as indicator ("low", "medium", "high", "critical") = "medium"
    status as indicator ("not-started", "in-progress", "blocked", "completed") = "not-started"
    startDate as date
    dueDate as date
    completedDate as date
    estimatedHours as number
    actualHours as number
    dependencies as array.number                    // Array of task IDs
    tags as array.text                              // Array of text tags
end type
```

---

## Deep Nesting Example

Access data **5 levels deep**:

```javascript
var company as CompanyType = record {
    name: "TechCorp",
    departments: {
        record {
            name: "Engineering",
            projects: {
                record {
                    name: "Project Alpha",
                    tasks: {
                        record {
                            title: "Design API",
                            assignedTo: record {
                                name: "Alice Smith",
                                address: record {
                                    city: "San Francisco"
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// Access deeply nested field (5 levels)
var city = company.departments[0].projects[0].tasks[0].assignedTo.address.city
print city  // "San Francisco"
```

---

## Key Features of Complex Records

### 1. Multiple Inheritance Levels
```javascript
PersonType → EmployeeType → ManagerType → ExecutiveType
```

### 2. Nested Records
```javascript
record type EmployeeType
    address as AddressType        // Nested
    emergencyContact as PersonType // Nested
end type
```

### 3. Arrays of Records
```javascript
record type DepartmentType
    employees as array.record     // Array of Employee records
    projects as array.record      // Array of Project records
end type
```

### 4. All Data Types
```javascript
record type EmployeeType
    // Text
    firstName as text
    lastName as text
    
    // Number
    employeeId as number
    salary as number
    
    // Flag (boolean)
    isFullTime as flag
    
    // Date
    hireDate as date
    
    // Indicator (enum)
    status as indicator ("active", "on-leave", "terminated") = "active"
    
    // Range-constrained number
    performanceRating as number 0.0..5.0
    
    // Arrays
    skills as array.text
    projectIds as array.number
    
    // Nested records
    address as AddressType
end type
```

### 5. Field Attributes (Validation & Defaults)
```javascript
record type UserType
    // Mandatory fields (must be provided)
    username as text mandatory maxlength:50
    email as text mandatory maxlength:100
    
    // Optional fields with defaults
    age as number default:0
    bio as text maxlength:500
    isActive as flag default:true
    loginCount as number default:0
    
    // Length-constrained text
    displayName as text maxlength:100
    website as text maxlength:200
end type

// Creating instance - only mandatory fields required
var user as UserType = record {
    username: "alice",
    email: "alice@example.com"
    // Other fields use defaults or remain unset
}

// Field attributes provide:
// - mandatory: Enforces required fields
// - default:value: Automatic default values
// - maxlength:n: Text length validation
```

### 6. Type Checking with Inheritance
```javascript
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

## Where to Learn More

### Full Working Examples
- **doc/examples/07_records.ebs** - Basic record usage (160 lines)
- **doc/examples/10_complex_records.ebs** - **Complete complex example (600+ lines)**

### Detailed Documentation
- **doc/COMPLEX_RECORD_DEFINITIONS.md** - Quick reference with patterns and best practices
- **doc/EBS2_LANGUAGE_SPEC.md** - Complete language specification (lines 830-1210)

---

## Summary

**Complex record definitions in EBS2 can be:**

✅ **4+ levels of inheritance** (Person → Employee → Manager → Executive)  
✅ **5+ levels deep nesting** (company.departments[0].projects[0].tasks[0].assignedTo.address.city)  
✅ **Arrays of records** (employees as array.record)  
✅ **All data types** (text, number, flag, date, indicator, ranges, arrays)  
✅ **Field attributes** (mandatory, default:value, maxlength:n)  
✅ **Type-safe** (indicator enums, range constraints, length validation)  
✅ **Recursive** (subdepartments within departments)  
✅ **Real-world ready** (enterprise-level data modeling)

The **10_complex_records.ebs** example demonstrates all of these features in a complete, working company management system!

---

*See doc/examples/10_complex_records.ebs for the full 600+ line working example.*

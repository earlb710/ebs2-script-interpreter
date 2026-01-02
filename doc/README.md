# EBS2 Language Specification

This directory contains the complete specification for EBS2 - a redesigned scripting language focused on child-friendliness while maintaining power for advanced users.

## 📚 Start Here

**[EBS2_INDEX.md](EBS2_INDEX.md)** - Central navigation guide with reading paths for all audiences

## 📦 Documentation Suite

| Document | Purpose |
|----------|---------|
| [EBS2_SPECIFICATION_SUMMARY.md](EBS2_SPECIFICATION_SUMMARY.md) | Executive overview and key decisions |
| [EBS2_REQUIREMENTS.md](EBS2_REQUIREMENTS.md) | Core requirements and design goals |
| [EBS2_LANGUAGE_SPEC.md](EBS2_LANGUAGE_SPEC.md) | Complete formal specification (69KB) |
| [EBS2_IMPLEMENTATION_ROADMAP.md](EBS2_IMPLEMENTATION_ROADMAP.md) | 12-month phased implementation plan |
| [EBS1_VS_EBS2_COMPARISON.md](EBS1_VS_EBS2_COMPARISON.md) | Migration guide from EBS1 to EBS2 |
| [EBS2_QUICK_START_GUIDE.md](EBS2_QUICK_START_GUIDE.md) | Beginner-friendly tutorial |
| [EBS2_QUICK_REFERENCE.md](EBS2_QUICK_REFERENCE.md) | One-page quick reference card |
| [COMPLEX_RECORD_DEFINITIONS.md](COMPLEX_RECORD_DEFINITIONS.md) | Comprehensive guide to complex record structures |
| [ANSWER_COMPLEX_RECORDS.md](ANSWER_COMPLEX_RECORDS.md) | Quick answer: What do complex records look like? |

## 🛠️ Implementation Guides

| Document | Purpose |
|----------|---------|
| [RUNNING_APPLICATIONS.md](RUNNING_APPLICATIONS.md) | Guide for running the 3 main executables |
| [JSON_PACKAGE_README.md](JSON_PACKAGE_README.md) | JSON datatype implementation details |
| [CONSOLE_PACKAGE_README.md](CONSOLE_PACKAGE_README.md) | Console package overview |
| [implementation/](implementation/) | Implementation-specific documentation (parser, JSON, etc.) |

## 📂 Subdirectories

- **[examples/](examples/)** - Example EBS2 scripts demonstrating language features
- **[implementation/](implementation/)** - Implementation documentation for specific components

## 🎯 Quick Overview

**EBS2** redesigns the EBS scripting language with:
- 🗣️ Natural language syntax for children (ages 8-16)
- 🌐 Dual-runtime architecture (HTML5 + Java)
- 📈 Progressive complexity (simple → advanced)
- 🧱 Visual block editor for beginners

### Example

```javascript
program HelloWorld

variables
    var name as text = "Alice"
    var age as number = 10
end

main
    if age is greater than 12 then
        print "You're a teenager!"
    else
        print "You're a child!"
    end
end
```

## 📊 Specification Stats

- **Core Specification:** 172KB
- **Total Lines:** 6,974
- **Code Examples:** 200+
- **Implementation Timeline:** 12 months

## 🚀 Status

✅ **Specification Complete** - Ready for review and implementation  
✅ **Documentation Cleaned Up** - Organized structure (Jan 2026)  
⏳ **Awaiting Stakeholder Approval**

---

**Full Index:** [EBS2_INDEX.md](EBS2_INDEX.md)

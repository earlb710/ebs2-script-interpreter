# EBS2 Screen Definition Guide

**Version:** 2.0.0  
**Last Updated:** December 30, 2025  
**Status:** Complete Specification

---

## Table of Contents

1. [Introduction](#introduction)
2. [Screen Basics](#screen-basics)
3. [Screen Structure](#screen-structure)
4. [Screen Properties](#screen-properties)
5. [Layout Systems](#layout-systems)
6. [UI Components](#ui-components)
7. [Event Handling](#event-handling)
8. [Screen Areas](#screen-areas)
9. [Screen Management](#screen-management)
10. [Advanced Features](#advanced-features)
11. [Best Practices](#best-practices)
12. [Complete Examples](#complete-examples)

---

## Introduction

Screens in EBS2 are the primary way to create graphical user interfaces. They provide a declarative, child-friendly syntax for building interactive applications that run on both web browsers (HTML5) and desktop (JavaFX).

### Key Features

- **Declarative syntax** - Define what you want, not how to build it
- **Cross-platform** - Runs on web and desktop
- **Responsive** - Automatically adapts to different screen sizes
- **Event-driven** - Built-in support for user interactions
- **Child-friendly** - Natural language keywords and structure

### Screen Philosophy

EBS2 screens are designed with progressive complexity:
- **Beginner**: Simple screens with basic components
- **Intermediate**: Layouts, multiple components, event handling
- **Advanced**: Multiple areas, canvas graphics, custom styling

---

## Screen Basics

### Minimum Screen Definition

The simplest screen contains just a name:

```javascript
screen MyScreen
end screen
```

### Basic Screen with Title

```javascript
screen WelcomeScreen
    title "Welcome to EBS2"
end screen
```

### Displaying a Screen

Use the `show` keyword to display a screen:

```javascript
main
    show screen WelcomeScreen
end main
```

### Hiding a Screen

```javascript
hide screen WelcomeScreen
```

### Printing Screen as JSON

Use the `print` command (without the `screen` keyword) to output the JSON representation of a screen. This is useful for saving and loading screen definitions:

```javascript
// Print screen as JSON string
print WelcomeScreen

// Save to variable using .toJSON()
var screenJson = WelcomeScreen.toJSON()

// Alternative: Use .toString() (returns same JSON string)
var screenStr = WelcomeScreen.toString()

// Later, load screen from JSON
var loadedScreen = screen.fromJSON(screenJson)
```

The JSON representation includes all screen properties, components, and their configurations, allowing screens to be serialized for storage or transmission.

---

## Screen Structure

### General Structure

```javascript
// Traditional syntax
screen ScreenName
    // Screen properties
    title "Screen Title"
    width 800
    height 600
    
    // Layout definition
    layout vertical spacing 10 padding 20
    
    // UI Components
    label MyLabel
        text "Hello"
    end label
    
    button MyButton
        text "Click"
        if clicked
            // Handle click
        end when
    end button
end screen

// Alternative: Curly braces syntax with = assignment
screen ScreenName {
    title = "Screen Title";
    width = 800;
    height = 600;
    
    // Layout definition
    layout vertical spacing 10 padding 20
    
    // UI Components
    label MyLabel
        text "Hello"
    end label
    
    button MyButton
        text "Click"
        if clicked
            // Handle click
        end when
    end button
}
```

**Note:** Both syntax styles are supported. The curly braces syntax uses `=` for property assignment and semicolons are optional.

### Naming Conventions

Screen names should:
- Start with a capital letter
- Use PascalCase (e.g., `MainWindow`, `LoginScreen`)
- Be descriptive and meaningful
- Not contain spaces or special characters

**Good Examples:**
- `LoginScreen`
- `UserProfile`
- `GameBoard`
- `SettingsWindow`

**Bad Examples:**
- `screen1` (not descriptive)
- `my screen` (contains space)
- `login-form` (contains hyphen)

---

## Screen Properties

### Title

Sets the window/page title:

```javascript
// Traditional syntax
screen MyScreen
    title "Application Name"
end screen

// Curly braces syntax
screen MyScreen {
    title = "Application Name";
}
```

### Size Properties

#### Width and Height

```javascript
// Traditional syntax
screen MyScreen
    width 800        // Pixels
    height 600       // Pixels
end screen

// Curly braces syntax
screen MyScreen {
    width = 800;     // Pixels
    height = 600;    // Pixels
}
```

#### Size Keywords

```javascript
screen MyScreen
    width fill       // Fill available width
    height fill      // Fill available height
end screen
```

```javascript
screen MyScreen
    width minimum    // Minimum size needed for content
    height minimum   // Minimum size needed for content
end screen
```

### Background Properties

#### Background Color

```javascript
screen MyScreen
    background white
end screen
```

Supported colors:
- Named colors: `white`, `black`, `red`, `blue`, `green`, `yellow`, `gray`, `orange`, `purple`, `pink`
- Hex colors: `"#FF0000"`, `"#00FF00"`, `"#0000FF"`
- RGB: `rgb(255, 0, 0)`

#### Background Image

```javascript
screen MyScreen
    background image "background.png"
    background size cover
end screen
```

Background size options:
- `cover` - Scale to cover entire screen
- `contain` - Scale to fit within screen
- `fill` - Stretch to fill screen
- `actual` - Use actual image size

### Padding and Margins

```javascript
screen MyScreen
    padding 20           // All sides
    padding 10 20        // Vertical Horizontal
    padding 10 20 15 25  // Top Right Bottom Left
end screen
```

### Border Properties

```javascript
screen MyScreen
    border width 2
    border color black
    border style solid
    border radius 10
end screen
```

Border styles:
- `solid` - Solid line
- `dashed` - Dashed line
- `dotted` - Dotted line
- `none` - No border

### Visibility

```javascript
// Traditional syntax
screen MyScreen
    visible yes    // Default
    enabled yes    // Default
end screen

// Curly braces syntax with yes/no
screen MyScreen {
    visible = yes;   // Default
    enabled = yes;   // Default
}

// Curly braces syntax with true/false (equivalent)
screen MyScreen {
    visible = true;  // Same as yes
    enabled = true;  // Same as yes
}
```

**Note:** For flag properties, both `yes/no` and `true/false` are supported and equivalent.

### Modal Properties

```javascript
// Traditional syntax
screen DialogScreen
    modal yes                // Blocks other screens
    closable yes            // Can be closed by user
    resizable no            // Cannot be resized
    movable yes             // Can be moved
end screen

// Alternative: Curly braces syntax with = assignment
screen DialogScreen {
    modal = yes;             // Blocks other screens
    closable = yes;          // Can be closed by user
    resizable = no;          // Cannot be resized
    movable = yes;           // Can be moved
}

// Both true/false and yes/no are supported for flag properties
screen DialogScreen {
    modal = true;            // Same as yes
    closable = true;         // Same as yes
    resizable = false;       // Same as no
    movable = true;          // Same as yes
}
```

**Note:** 
- Both syntax styles are supported and equivalent
- For flag properties, you can use either `yes/no` or `true/false`
- Curly braces syntax uses `=` for assignment and semicolons are optional

---

## Layout Systems

**Note:** All layout examples below can use either `end` keywords or curly braces `{}`. Both forms are valid and equivalent.

### No Layout (Absolute Positioning)

Components are positioned absolutely:

```javascript
// Using end keywords
screen MyScreen
    label MyLabel
        text "Hello"
        at x:10 y:10
    end label
    
    button MyButton
        text "Click"
        at x:10 y:50
    end button
end screen

// Using curly braces (alternative)
screen MyScreen {
    label MyLabel {
        text "Hello"
        at x:10 y:10
    }
    
    button MyButton {
        text "Click"
        at x:10 y:50
    }
}
```

### Vertical Layout

Stack components vertically:

```javascript
screen MyScreen
    layout vertical spacing 10 padding 20
    
    label Label1
        text "First"
    end label
    
    label Label2
        text "Second"
    end label
    
    label Label3
        text "Third"
    end label
end screen
```

Layout properties:
- `spacing` - Space between components (pixels)
- `padding` - Space around layout edges (pixels)
- `align` - Alignment: `left`, `center`, `right`, `top`, `bottom`

### Horizontal Layout

Arrange components horizontally:

```javascript
screen MyScreen
    layout horizontal spacing 10 padding 20 align center
    
    button Previous
        text "Previous"
    end button
    
    label PageNumber
        text "Page 1"
    end label
    
    button Next
        text "Next"
    end button
end screen
```

### Grid Layout

Arrange in rows and columns:

```javascript
screen MyScreen
    layout grid rows 3 columns 2 spacing 10 padding 20
    
    // Components fill grid left-to-right, top-to-bottom
    label Cell1
        text "Row 1, Col 1"
    end label
    
    label Cell2
        text "Row 1, Col 2"
    end label
    
    label Cell3
        text "Row 2, Col 1"
    end label
    
    label Cell4
        text "Row 2, Col 2"
    end label
end screen
```

Grid-specific properties:
- `rows` - Number of rows
- `columns` - Number of columns
- `spacing` - Space between cells
- `padding` - Space around grid
- `fill` - How components fill cells: `both`, `horizontal`, `vertical`, `none`

### Form Layout

Specialized layout for forms with labels and inputs:

```javascript
screen MyScreen
    layout form spacing 10 padding 20 label width 120
    
    // Automatically creates label-input pairs
    field "Name:"
        textbox NameInput
            placeholder "Enter name"
        end textbox
    end field
    
    field "Age:"
        numberbox AgeInput
            minimum 1
            maximum 120
        end numberbox
    end field
    
    field "Email:"
        textbox EmailInput
            placeholder "your@email.com"
        end textbox
    end field
end screen
```

### Flow Layout

Components flow and wrap automatically:

```javascript
screen MyScreen
    layout flow spacing 10 padding 20 direction horizontal
    
    // Components wrap to next line when space runs out
    button Button1
        text "Button 1"
    end button
    
    button Button2
        text "Button 2"
    end button
    
    // ... more buttons
end screen
```

---

## UI Components

### Labels

Display text:

```javascript
label MyLabel
    text "This is a label"
    size small | medium | large
    style normal | bold | italic | underline
    color red | blue | green | black | "#FF0000"
    align left | center | right
    font "Arial" | "Times" | "Courier"
    font size 12
    wrap yes | no
    selectable yes | no
end label
```

**Full Example:**

```javascript
label TitleLabel
    text "Welcome to My Application"
    size large
    style bold
    color "#2E86C1"
    align center
    font "Arial"
    wrap yes
end label
```

### Buttons

Clickable buttons:

```javascript
button MyButton
    text "Click Me"
    icon "icon.png"
    icon position left | right | top | bottom
    style primary | secondary | success | danger | warning | info
    size small | medium | large
    enabled yes | no
    tooltip "Click this button to continue"
    
    if clicked
        print "Button was clicked!"
    end when
    
    if double clicked
        print "Button was double-clicked!"
    end when
    
    if right clicked
        print "Button was right-clicked!"
    end when
end button
```

**Button Styles:**
- `primary` - Main action button (blue)
- `secondary` - Secondary action (gray)
- `success` - Success action (green)
- `danger` - Destructive action (red)
- `warning` - Warning action (orange)
- `info` - Information action (light blue)

**Full Example:**

```javascript
button SubmitButton
    text "Submit Form"
    icon "check.png"
    icon position left
    style success
    size large
    tooltip "Submit the form"
    
    if clicked
        // Validate and submit
        var name = get text from NameInput
        if name is empty
            show error "Please enter your name"
        else
            submit form
            show success "Form submitted!"
        end if
    end when
end button
```

### Text Input

Single-line text input:

```javascript
textbox MyInput
    placeholder "Enter text..."
    default "Initial value"
    max length 100
    min length 0
    width 200
    password no | yes
    multiline no | yes
    readonly no | yes
    required no | yes
    pattern "regex pattern"
    
    if changed
        var text = get text from MyInput
        print "Text changed to: " + text
    end when
    
    if enter pressed
        var text = get text from MyInput
        print "Enter pressed with: " + text
    end when
    
    if focused
        print "Input gained focus"
    end when
    
    if blurred
        print "Input lost focus"
    end when
end textbox
```

**Password Input:**

```javascript
textbox PasswordInput
    placeholder "Enter password"
    password yes
    max length 50
    required yes
end textbox
```

**Multiline Text:**

```javascript
textbox CommentsInput
    placeholder "Enter comments..."
    multiline yes
    width 300
    height 100
    max length 500
end textbox
```

### Number Input

Numeric input with validation:

```javascript
numberbox MyNumber
    minimum 0
    maximum 100
    default 50
    step 1
    decimals 0 | 2 | any
    unit "px" | "%" | "km"
    width 100
    
    if changed
        var value = get value from MyNumber
        print "Value changed to: " + value
    end when
    
    if increased
        print "Value increased"
    end when
    
    if decreased
        print "Value decreased"
    end when
end numberbox
```

**Full Example:**

```javascript
numberbox AgeInput
    minimum 1
    maximum 120
    default 10
    step 1
    decimals 0
    unit "years"
    
    if changed
        var age = get value from AgeInput
        if age >= 18
            enable SubmitButton
        else
            disable SubmitButton
        end if
    end when
end numberbox
```

### Checkboxes

Toggle options:

```javascript
checkbox MyCheckbox
    text "I agree to the terms"
    checked no | yes
    enabled yes | no
    required no | yes
    
    if toggled
        var isChecked = is checked MyCheckbox
        if isChecked
            print "Checkbox is now checked"
        else
            print "Checkbox is now unchecked"
        end if
    end when
    
    if clicked
        print "Checkbox was clicked"
    end when
end checkbox
```

**Full Example:**

```javascript
checkbox NewsletterCheckbox
    text "Subscribe to our newsletter"
    checked no
    
    if toggled
        var subscribed = is checked NewsletterCheckbox
        if subscribed
            show info "You will receive our newsletter"
        else
            show info "You will not receive our newsletter"
        end if
    end when
end checkbox
```

### Radio Buttons

Mutually exclusive options:

```javascript
radiogroup DifficultyLevel
    radio Easy
        text "Easy"
        checked yes
        tooltip "For beginners"
    end radio
    
    radio Medium
        text "Medium"
        tooltip "For experienced players"
    end radio
    
    radio Hard
        text "Hard"
        tooltip "For experts"
    end radio
    
    if changed
        var selected = get selected from DifficultyLevel
        print "Selected difficulty: " + selected
    end when
end radiogroup
```

**Full Example with Logic:**

```javascript
radiogroup PaymentMethod
    radio CreditCard
        text "Credit Card"
        checked yes
    end radio
    
    radio PayPal
        text "PayPal"
    end radio
    
    radio BankTransfer
        text "Bank Transfer"
    end radio
    
    if changed
        var method = get selected from PaymentMethod
        
        if method equals "CreditCard"
            show CreditCardForm
            hide PayPalForm
            hide BankForm
        else if method equals "PayPal"
            hide CreditCardForm
            show PayPalForm
            hide BankForm
        else if method equals "BankTransfer"
            hide CreditCardForm
            hide PayPalForm
            show BankForm
        end if
    end when
end radiogroup
```

### Dropdowns

Selection from list:

```javascript
dropdown ColorChoice
    option "Red"
    option "Green"
    option "Blue"
    option "Yellow"
    selected "Red"
    placeholder "Choose a color"
    width 150
    max visible items 5
    searchable yes | no
    
    if changed
        var color = get selected from ColorChoice
        print "Selected color: " + color
    end when
    
    if opened
        print "Dropdown opened"
    end when
    
    if closed
        print "Dropdown closed"
    end when
end dropdown
```

**Full Example with Dynamic Options:**

```javascript
dropdown CountryChoice
    selected "USA"
    placeholder "Select country"
    searchable yes
    
    if changed
        var country = get selected from CountryChoice
        
        // Update states based on country
        clear options from StateChoice
        if country equals "USA"
            add options to StateChoice: "California", "Texas", "New York"
        else if country equals "Canada"
            add options to StateChoice: "Ontario", "Quebec", "Alberta"
        end if
    end when
end dropdown

dropdown StateChoice
    placeholder "Select state"
end dropdown
```

### Lists

Scrollable list of items:

```javascript
listbox FruitList
    items "Apple", "Banana", "Cherry", "Date", "Elderberry"
    selected "Apple"
    multiple selection no | yes
    width 200
    height 150
    visible items 5
    
    if selected
        var fruit = get selected from FruitList
        print "Selected: " + fruit
    end when
    
    if double clicked
        var fruit = get selected from FruitList
        print "Double-clicked: " + fruit
    end when
end listbox
```

**Multiple Selection:**

```javascript
listbox FavoritesList
    items "Reading", "Music", "Sports", "Cooking", "Travel"
    multiple selection yes
    height 200
    
    if changed
        var favorites = get all selected from FavoritesList
        print "You selected " + favorites.length + " items"
        
        for each item in favorites
            print "  - " + item
        end for
    end when
end listbox
```

### Sliders

Range selection:

```javascript
slider VolumeSlider
    minimum 0
    maximum 100
    value 50
    step 1
    orientation horizontal | vertical
    show labels yes | no
    show ticks yes | no
    tick interval 10
    width 300
    
    if changed
        var volume = get value from VolumeSlider
        print "Volume: " + volume + "%"
    end when
    
    if released
        var volume = get value from VolumeSlider
        print "Final volume: " + volume + "%"
    end when
end slider
```

**Full Example with Visual Feedback:**

```javascript
slider BrightnessSlider
    minimum 0
    maximum 100
    value 50
    step 5
    show labels yes
    show ticks yes
    tick interval 25
    
    if changed
        var brightness = get value from BrightnessSlider
        set brightness of screen to brightness
        update label BrightnessLabel to "Brightness: " + brightness + "%"
    end when
end slider

label BrightnessLabel
    text "Brightness: 50%"
end label
```

### Progress Bars

Show progress:

```javascript
progressbar DownloadProgress
    minimum 0
    maximum 100
    value 0
    show percentage yes | no
    style default | striped | animated
    color blue | green | red | "#0000FF"
    height 30
    width 300
    
    // Update progress
    set value of DownloadProgress to 50
end progressbar
```

**Indeterminate Progress:**

```javascript
progressbar LoadingIndicator
    indeterminate yes
    text "Loading..."
    style animated
end progressbar
```

### Canvas

Drawing area for graphics:

```javascript
canvas DrawingCanvas
    width 400
    height 300
    background white
    
    when draw
        // Drawing commands
        draw rectangle at x:10 y:10 width:50 height:50 color:red fill:yes
        draw circle at x:100 y:100 radius:30 color:blue fill:no
        draw line from x1:0 y1:0 to x2:100 y2:100 color:black width:2
        draw text "Hello" at x:150 y:150 font:"Arial" size:20 color:black
    end when
    
    if clicked at x and y
        draw circle at x:x y:y radius:5 color:black fill:yes
        refresh canvas
    end when
    
    if mouse moved at x and y
        // Track mouse position
    end when
    
    if mouse dragged at x and y
        draw circle at x:x y:y radius:3 color:blue fill:yes
        refresh canvas
    end when
end canvas
```

**Drawing Commands:**

```javascript
// Shapes
draw rectangle at x:X y:Y width:W height:H color:C fill:yes/no
draw circle at x:X y:Y radius:R color:C fill:yes/no
draw ellipse at x:X y:Y width:W height:H color:C fill:yes/no
draw polygon points:[x1,y1, x2,y2, x3,y3] color:C fill:yes/no
draw arc at x:X y:Y radius:R start:0 end:180 color:C

// Lines
draw line from x1:X1 y1:Y1 to x2:X2 y2:Y2 color:C width:W
draw path points:[x1,y1, x2,y2, x3,y3] color:C width:W

// Text
draw text "message" at x:X y:Y font:"Arial" size:12 color:C align:left

// Images
draw image "file.png" at x:X y:Y width:W height:H

// Transformations
rotate by degrees around x:X y:Y
scale by factor
translate by x:X y:Y

// Canvas operations
clear canvas
clear area at x:X y:Y width:W height:H
refresh canvas
save canvas as "image.png"
```

### Tables

Display tabular data:

```javascript
table DataTable
    columns "Name", "Age", "City"
    rows data
    selectable yes
    sortable yes
    filterable yes
    editable no | yes
    page size 10
    width fill
    height 300
    
    if row selected
        var row = get selected row from DataTable
        print "Selected: " + row.Name
    end when
    
    if cell edited at row R column C
        var value = get value at row R column C from DataTable
        print "Cell edited: " + value
    end when
end table
```

**Full Example:**

```javascript
var users = [
    {Name: "Alice", Age: 30, City: "New York"},
    {Name: "Bob", Age: 25, City: "Los Angeles"},
    {Name: "Charlie", Age: 35, City: "Chicago"}
]

table UserTable
    columns "Name", "Age", "City"
    rows users
    selectable yes
    sortable yes
    filterable yes
    page size 10
    
    column "Name"
        width 150
        align left
    end column
    
    column "Age"
        width 80
        align center
    end column
    
    column "City"
        width 150
        align left
    end column
    
    if row selected
        var user = get selected row from UserTable
        show info "Selected: " + user.Name
    end when
end table
```

### Images

Display images:

```javascript
image MyImage
    source "photo.jpg"
    width 200
    height 150
    fit cover | contain | fill | actual
    align center
    tooltip "My Photo"
    
    if clicked
        show fullscreen image MyImage
    end when
    
    if loaded
        print "Image loaded successfully"
    end when
    
    if error
        print "Failed to load image"
        set source of MyImage to "placeholder.png"
    end when
end image
```

**Image Fit Modes:**
- `cover` - Scale to cover area (may crop)
- `contain` - Scale to fit within area (may have empty space)
- `fill` - Stretch to fill area (may distort)
- `actual` - Show at actual size

---

## Event Handling

### Click Events

```javascript
button MyButton
    text "Click Me"
    
    if clicked
        print "Button clicked!"
    end when
    
    if double clicked
        print "Button double-clicked!"
    end when
    
    if right clicked
        print "Button right-clicked!"
    end when
end button
```

### Input Events

```javascript
textbox MyInput
    if changed
        var text = get text from MyInput
        print "Text: " + text
    end when
    
    if enter pressed
        var text = get text from MyInput
        submit text
    end when
    
    if focused
        print "Input focused"
    end when
    
    if blurred
        print "Input lost focus"
    end when
end textbox
```

### Mouse Events

```javascript
canvas MyCanvas
    if clicked at x and y
        print "Clicked at: " + x + ", " + y
    end when
    
    if mouse moved at x and y
        update label to "Mouse: " + x + ", " + y
    end when
    
    if mouse dragged at x and y
        draw point at x:x y:y
    end when
    
    if mouse entered
        print "Mouse entered canvas"
    end when
    
    if mouse exited
        print "Mouse left canvas"
    end when
end canvas
```

### Keyboard Events

```javascript
screen MyScreen
    if key pressed key
        print "Key pressed: " + key
    end when
    
    if key released key
        print "Key released: " + key
    end when
    
    if key "Enter" pressed
        submit form
    end when
    
    if key "Escape" pressed
        hide screen MyScreen
    end when
end screen
```

### Window Events

```javascript
screen MyScreen
    if shown
        print "Screen is now visible"
    end when
    
    if hidden
        print "Screen is now hidden"
    end when
    
    if resized to width and height
        print "New size: " + width + "x" + height
    end when
    
    if closed
        save data
        print "Screen closed"
    end when
end screen
```

---

## Screen Areas

Divide screen into sections:

```javascript
screen MyScreen
    area Header at top height 60
        layout horizontal align center
        
        label Title
            text "My Application"
            size large
        end label
    end area
    
    area Sidebar at left width 200
        layout vertical spacing 10
        
        button Home
            text "Home"
        end button
        
        button Settings
            text "Settings"
        end button
    end area
    
    area MainContent at center
        layout vertical
        
        label Content
            text "Main content goes here"
        end label
    end area
    
    area Footer at bottom height 40
        layout horizontal align center
        
        label Copyright
            text "© 2025 My App"
        end label
    end area
end screen
```

**Area Positions:**
- `at top` - Top of screen
- `at bottom` - Bottom of screen
- `at left` - Left side
- `at right` - Right side
- `at center` - Central area
- `at top left` - Top-left corner
- `at top right` - Top-right corner
- `at bottom left` - Bottom-left corner
- `at bottom right` - Bottom-right corner

**Area Properties:**
- `width` - Fixed width in pixels or `fill`
- `height` - Fixed height in pixels or `fill`
- `min width` - Minimum width
- `min height` - Minimum height
- `max width` - Maximum width
- `max height` - Maximum height
- `background` - Background color
- `border` - Border properties
- `padding` - Internal padding
- `scrollable` - Allow scrolling (yes/no)

---

## Screen Management

### Showing Screens

```javascript
// Show screen
show screen MyScreen

// Show as modal (blocks other screens)
show screen DialogScreen as modal

// Show at position
show screen PopupScreen at x:100 y:100

// Show maximized
show screen MainScreen maximized

// Show fullscreen
show screen GameScreen fullscreen
```

### Hiding Screens

```javascript
// Hide specific screen
hide screen MyScreen

// Hide current screen
hide current screen

// Hide all screens
hide all screens

// Hide with transition
hide screen MyScreen with fade duration 500
```

### Switching Screens

```javascript
// Switch to new screen
switch to screen NewScreen

// Switch with transition
switch to screen NewScreen with slide direction left duration 300

// Close current and show new
close current screen and show screen NewScreen
```

### Screen States

```javascript
// Check if screen is visible
if screen MyScreen is visible
    print "Screen is showing"
end if

// Check if screen exists
if screen MyScreen exists
    hide screen MyScreen
end if

// Get current screen
var current = get current screen
print "Current screen: " + current
```

---

## Advanced Features

### Custom Styles

```javascript
screen MyScreen
    style "custom.css"  // Load external stylesheet
    
    label StyledLabel
        class "title-large primary-color"
        style "font-weight: bold; color: #FF0000;"
    end label
end screen
```

### Animations

```javascript
button AnimatedButton
    text "Hover Me"
    
    if mouse entered
        animate button
            scale to 1.1
            duration 200
            easing ease-in-out
        end animate
    end when
    
    if mouse exited
        animate button
            scale to 1.0
            duration 200
        end animate
    end when
end button
```

**Animation Properties:**
- `scale to` - Scale factor
- `rotate to` - Rotation in degrees
- `move to x:X y:Y` - Position
- `fade to` - Opacity (0-1)
- `duration` - Animation length in milliseconds
- `easing` - Easing function: `linear`, `ease-in`, `ease-out`, `ease-in-out`
- `delay` - Delay before animation starts

### Drag and Drop

```javascript
label DraggableItem
    text "Drag Me"
    draggable yes
    
    if drag started
        set drag data to "item1"
    end when
end label

area DropZone
    droppable yes
    
    if drop accepted
        var data = get drop data
        print "Dropped: " + data
        add label to DropZone
            text "Dropped " + data
        end label
    end when
    
    if drag entered
        set background of DropZone to "#E0E0E0"
    end when
    
    if drag exited
        set background of DropZone to "white"
    end when
end area
```

### Context Menus

```javascript
label MyLabel
    text "Right-click me"
    
    context menu
        menu item "Copy"
            if clicked
                copy text to clipboard
            end when
        end item
        
        menu item "Paste"
            if clicked
                paste from clipboard
            end when
        end item
        
        menu separator
        
        menu item "Delete"
            if clicked
                delete MyLabel
            end when
        end item
    end menu
end label
```

### Tooltips

```javascript
button HelpButton
    text "?"
    tooltip "Click for help"
    tooltip position top
    tooltip delay 500
    tooltip style info
end button
```

### Validation

```javascript
textbox EmailInput
    placeholder "Enter email"
    validate email
    required yes
    
    if validated
        print "Email is valid"
        enable SubmitButton
    end when
    
    if validation failed with message
        print "Error: " + message
        show error message
        disable SubmitButton
    end when
end textbox

textbox PhoneInput
    placeholder "Enter phone"
    validate pattern "^\d{3}-\d{3}-\d{4}$"
    error message "Format: 123-456-7890"
end textbox
```

**Built-in Validators:**
- `email` - Email format
- `url` - URL format
- `number` - Numeric value
- `integer` - Integer value
- `pattern "regex"` - Custom regex pattern
- `min length N` - Minimum length
- `max length N` - Maximum length
- `range min:N max:M` - Value range

### Screen Serialization and JSON

Screens can be serialized to JSON format for saving and loading. This enables dynamic screen creation, screen templates, and screen persistence.

#### Printing Screen as JSON

Use `print` (without the `screen` keyword) to output the JSON representation:

```javascript
screen MyScreen
    title "Sample Screen"
    width 800
    height 600
    
    label WelcomeLabel
        text "Welcome!"
    end label
    
    button ClickButton
        text "Click Me"
    end button
end screen

main
    // Print the screen definition as JSON
    print MyScreen
end main
```

This outputs a JSON string representing the entire screen structure, including:
- Screen properties (title, width, height, etc.)
- All components and their properties
- Layout configuration
- Event handlers (as references)

#### Converting Screen to JSON

```javascript
// Get screen as JSON string using .toJSON()
var screenJson = MyScreen.toJSON()

// Alternative: Use .toString() (returns same JSON string)
var screenStr = MyScreen.toString()

// Save to file
write screenJson to file "screens/myscreen.json"

// Send over network
send screenJson to server
```

#### Loading Screen from JSON

```javascript
// Load JSON from file
var jsonString = read file "screens/myscreen.json"

// Create screen from JSON
var loadedScreen = screen.fromJSON(jsonString)

// Show the loaded screen
show screen loadedScreen
```

#### Use Cases for JSON Serialization

**1. Screen Templates:**
```javascript
// Save a screen as a template using .toJSON()
var templateJson = MyScreen.toJSON()

// Alternative: Use .toString() (returns same JSON string)
var templateStr = MyScreen.toString()

write templateJson to file "templates/form_template.json"

// Later, load and customize
var template = screen.fromJSON(read file "templates/form_template.json")
```

**2. Dynamic Screen Creation:**
```javascript
// Generate screen JSON programmatically
var screenDef = {
    "type": "screen",
    "name": "DynamicScreen",
    "title": "Generated Screen",
    "components": [
        {
            "type": "label",
            "name": "Label1",
            "text": "Dynamic content"
        }
    ]
}

var dynamicScreen = screen.fromJSON(screenDef.toJSON())
show screen dynamicScreen
```

**3. Screen Version Control:**
```javascript
// Export current screen state using .toJSON()
var currentVersion = MyScreen.toJSON()

// Alternative: Use .toString() (returns same JSON string)
var currentVersionStr = MyScreen.toString()

write currentVersion to file "versions/myscreen_v2.json"

// Compare with previous version
var previousVersion = read file "versions/myscreen_v1.json"
```

**Important Notes:**
- The normal screen definition language is the preferred way to define screens
- JSON serialization is primarily for programmatic manipulation, templates, and persistence
- Event handlers are serialized as references, not executable code
- Complex nested screens may require special handling

---

## Best Practices

### 1. Screen Organization

**Do:**
```javascript
screen UserProfile
    // Group related components
    area PersonalInfo
        // Personal data components
    end area
    
    area ContactInfo
        // Contact components
    end area
    
    area Actions
        // Action buttons
    end area
end screen
```

**Don't:**
```javascript
screen UserProfile
    // All components mixed together without organization
    textbox Name
    button Save
    textbox Email
    button Cancel
    label Title
end screen
```

### 2. Naming Conventions

**Do:**
- Use descriptive names: `SubmitButton`, `EmailInput`, `ErrorLabel`
- Use PascalCase for screens: `MainWindow`, `LoginScreen`
- Use PascalCase for components: `FirstNameInput`, `SaveButton`

**Don't:**
- Use generic names: `Button1`, `Input2`, `Label3`
- Use unclear abbreviations: `FnInput`, `SvBtn`

### 3. Event Handling

**Do:**
```javascript
button SaveButton
    if clicked
        // Validate first
        if is valid form
            save data
            show success "Saved!"
        else
            show error "Please fix errors"
        end if
    end when
end button
```

**Don't:**
```javascript
button SaveButton
    if clicked
        // No validation, might save invalid data
        save data
    end when
end button
```

### 4. Responsive Design

**Do:**
```javascript
screen MyScreen
    layout vertical
    
    // Components adapt to available space
    textbox Input1
        width fill
    end textbox
end screen
```

**Don't:**
```javascript
screen MyScreen
    // Fixed sizes don't adapt
    textbox Input1
        width 500  // Too wide for small screens
    end textbox
end screen
```

### 5. User Feedback

**Do:**
```javascript
button DeleteButton
    if clicked
        show confirm "Are you sure you want to delete?"
        if confirmed
            delete item
            show success "Item deleted"
        end if
    end when
end button
```

**Don't:**
```javascript
button DeleteButton
    if clicked
        // No confirmation, no feedback
        delete item
    end when
end button
```

---

## Complete Examples

### Example 1: Login Screen

```javascript
screen LoginScreen
    title "Login"
    width 400
    height 300
    layout vertical spacing 15 padding 30 align center
    background "#F5F5F5"
    
    label TitleLabel
        text "Welcome Back"
        size large
        style bold
        color "#333333"
        align center
    end label
    
    textbox UsernameInput
        placeholder "Username"
        width fill
        max length 50
        required yes
    end textbox
    
    textbox PasswordInput
        placeholder "Password"
        password yes
        width fill
        max length 100
        required yes
        
        if enter pressed
            call login
        end when
    end textbox
    
    checkbox RememberMe
        text "Remember me"
    end checkbox
    
    button LoginButton
        text "Login"
        style primary
        width fill
        
        if clicked
            call login
        end when
    end button
    
    button ForgotPassword
        text "Forgot Password?"
        style secondary
        width fill
        
        if clicked
            switch to screen ForgotPasswordScreen
        end when
    end button
end screen

procedure login
    var username = get text from UsernameInput
    var password = get text from PasswordInput
    
    if username is empty or password is empty
        show error "Please enter username and password"
        return
    end if
    
    if authenticate user username with password
        if is checked RememberMe
            save credentials
        end if
        switch to screen MainScreen
    else
        show error "Invalid username or password"
        clear PasswordInput
        focus UsernameInput
    end if
end procedure
```

### Example 2: Settings Screen

```javascript
screen SettingsScreen
    title "Settings"
    width 600
    height 500
    
    area Sidebar at left width 150
        layout vertical spacing 5
        background "#EEEEEE"
        
        button GeneralTab
            text "General"
            style primary
            width fill
            if clicked
                show area GeneralSettings
                hide area AppearanceSettings
                hide area PrivacySettings
            end when
        end button
        
        button AppearanceTab
            text "Appearance"
            style secondary
            width fill
            if clicked
                hide area GeneralSettings
                show area AppearanceSettings
                hide area PrivacySettings
            end when
        end button
        
        button PrivacyTab
            text "Privacy"
            style secondary
            width fill
            if clicked
                hide area GeneralSettings
                hide area AppearanceSettings
                show area PrivacySettings
            end when
        end button
    end area
    
    area MainContent at center
        padding 20
        
        area GeneralSettings
            layout vertical spacing 15
            
            label GeneralTitle
                text "General Settings"
                size large
                style bold
            end label
            
            checkbox AutoStart
                text "Start application on system startup"
                checked no
            end checkbox
            
            checkbox CheckUpdates
                text "Automatically check for updates"
                checked yes
            end checkbox
            
            field "Language:"
                dropdown LanguageChoice
                    option "English"
                    option "Spanish"
                    option "French"
                    option "German"
                    selected "English"
                end dropdown
            end field
        end area
        
        area AppearanceSettings
            layout vertical spacing 15
            visible no
            
            label AppearanceTitle
                text "Appearance Settings"
                size large
                style bold
            end label
            
            radiogroup ThemeChoice
                radio LightTheme
                    text "Light Theme"
                    checked yes
                end radio
                
                radio DarkTheme
                    text "Dark Theme"
                end radio
                
                radio AutoTheme
                    text "Auto (Follow System)"
                end radio
                
                if changed
                    var theme = get selected from ThemeChoice
                    apply theme theme
                end when
            end radiogroup
            
            field "Font Size:"
                slider FontSizeSlider
                    minimum 10
                    maximum 20
                    value 14
                    step 1
                    show labels yes
                    
                    if changed
                        var size = get value from FontSizeSlider
                        set font size to size
                    end when
                end slider
            end field
        end area
        
        area PrivacySettings
            layout vertical spacing 15
            visible no
            
            label PrivacyTitle
                text "Privacy Settings"
                size large
                style bold
            end label
            
            checkbox Analytics
                text "Send anonymous usage data"
                checked yes
                
                if toggled
                    var enabled = is checked Analytics
                    set analytics enabled to enabled
                end when
            end checkbox
            
            checkbox CrashReports
                text "Send crash reports"
                checked yes
            end checkbox
            
            button ClearData
                text "Clear All Data"
                style danger
                
                if clicked
                    show confirm "This will delete all application data. Continue?"
                    if confirmed
                        clear all data
                        show success "All data cleared"
                    end if
                end when
            end button
        end area
    end area
    
    area Footer at bottom height 60
        layout horizontal spacing 10 padding 20 align right
        
        button CancelButton
            text "Cancel"
            style secondary
            
            if clicked
                hide screen SettingsScreen
            end when
        end button
        
        button SaveButton
            text "Save"
            style primary
            
            if clicked
                save settings
                show success "Settings saved"
                hide screen SettingsScreen
            end when
        end button
    end area
end screen
```

### Example 3: Dashboard Screen

```javascript
screen DashboardScreen
    title "Dashboard"
    width fill
    height fill
    
    area Header at top height 60
        layout horizontal spacing 20 padding 15 align space-between
        background "#2C3E50"
        
        label AppTitle
            text "My Dashboard"
            size large
            style bold
            color white
        end label
        
        area UserArea
            layout horizontal spacing 10 align center
            
            label UserName
                text "John Doe"
                color white
            end label
            
            image UserAvatar
                source "avatar.png"
                width 40
                height 40
                fit cover
                border radius 20
            end image
            
            button LogoutButton
                text "Logout"
                style secondary
                
                if clicked
                    logout user
                    switch to screen LoginScreen
                end when
            end button
        end area
    end area
    
    area MainContent at center
        padding 20
        layout grid rows 2 columns 2 spacing 20
        
        // Widget 1: Statistics
        area StatsWidget
            border width 1 color "#DDDDDD" radius 10
            padding 20
            background white
            
            label StatsTitle
                text "Statistics"
                style bold
                size medium
            end label
            
            area StatsGrid
                layout vertical spacing 10
                
                label TotalUsers
                    text "Total Users: 1,234"
                end label
                
                label ActiveUsers
                    text "Active Today: 456"
                end label
                
                label Revenue
                    text "Revenue: $12,345"
                end label
            end area
        end area
        
        // Widget 2: Recent Activity
        area ActivityWidget
            border width 1 color "#DDDDDD" radius 10
            padding 20
            background white
            
            label ActivityTitle
                text "Recent Activity"
                style bold
                size medium
            end label
            
            listbox ActivityList
                items [
                    "User John logged in",
                    "New order #1234",
                    "Payment received",
                    "Report generated"
                ]
                height 150
            end listbox
        end area
        
        // Widget 3: Chart
        area ChartWidget
            border width 1 color "#DDDDDD" radius 10
            padding 20
            background white
            
            label ChartTitle
                text "Sales Chart"
                style bold
                size medium
            end label
            
            canvas SalesChart
                width fill
                height 200
                
                when draw
                    // Draw simple bar chart
                    draw bars for sales data
                end when
            end canvas
        end area
        
        // Widget 4: Quick Actions
        area ActionsWidget
            border width 1 color "#DDDDDD" radius 10
            padding 20
            background white
            
            label ActionsTitle
                text "Quick Actions"
                style bold
                size medium
            end label
            
            area ActionButtons
                layout vertical spacing 10
                
                button NewUser
                    text "Add New User"
                    style primary
                    width fill
                    
                    if clicked
                        show screen AddUserScreen
                    end when
                end button
                
                button GenerateReport
                    text "Generate Report"
                    style secondary
                    width fill
                    
                    if clicked
                        generate report
                        show success "Report generated"
                    end when
                end button
                
                button ViewSettings
                    text "Settings"
                    style secondary
                    width fill
                    
                    if clicked
                        show screen SettingsScreen
                    end when
                end button
            end area
        end area
    end area
end screen
```

---

## Summary

EBS2 screens provide a powerful yet child-friendly way to create graphical user interfaces. Key takeaways:

1. **Declarative Syntax** - Define what you want, not how to build it
2. **Progressive Complexity** - Start simple, add features as needed
3. **Cross-Platform** - Works on web and desktop
4. **Event-Driven** - Built-in event handling
5. **Flexible Layouts** - Multiple layout systems for different needs
6. **Rich Components** - Comprehensive set of UI components
7. **Responsive** - Adapts to different screen sizes
8. **Best Practices** - Follow naming conventions and organization patterns

For more information, see:
- [EBS2 Language Specification](EBS2_LANGUAGE_SPEC.md)
- [EBS2 Quick Start Guide](EBS2_QUICK_START_GUIDE.md)
- [EBS2 Quick Reference](EBS2_QUICK_REFERENCE.md)

---

**Document Version:** 2.0.0  
**Last Updated:** December 30, 2025  
**Status:** Complete

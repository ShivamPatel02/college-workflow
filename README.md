# College BTech Workflow Management System

A comprehensive workflow management system for college BTech departments with multi-level approval chains, secure authentication, and role-based access control.

## Features

- **🔐 Secure Authentication**: Session-based login system with proper access control
- **Multi-Department Support**: CSE, ECE, MECH, CIVIL, EEE, IT
- **Role-Based Hierarchy**: STUDENT → COORDINATOR → HOD → DIRECTOR
- **Smart Approval Workflows**: 
  - Simple: Student → Coordinator (Leave, Lab Access)
  - Medium: Student → Coordinator → HOD (Fee Concession, Certificates)
  - Complex: Student → Coordinator → HOD → Director (Projects, Equipment)
- **Request Tracking**: Real-time status updates and approval history
- **H2 Database**: Lightweight in-memory database (no Docker required)
- **Session Management**: 30-minute session timeout with secure cookies

## Authentication System

### Security Features
- **Session-based Authentication**: Secure login/logout functionality
- **Access Control**: Protected routes require authentication
- **Session Timeout**: Automatic logout after 30 minutes of inactivity
- **Secure Cookies**: HTTP-only cookies with SameSite protection
- **Password Security**: Support for both plain text (demo) and hashed passwords

### Login Process
1. Access any protected page → Redirected to login
2. Enter valid credentials → Session created
3. Access granted to authorized pages
4. Logout → Session invalidated

### Authentication Flow
```
User Request → AuthInterceptor → Check Session → Allow/Redirect to Login
```

## Quick Start

1. **Run the Application**:
   ```bash
   run-h2.bat
   ```

2. **Access the System**:
   - Main Application: http://localhost:8080
   - H2 Database Console: http://localhost:8080/h2-console

3. **Test Users** (Username/Password: pass):
   - **Students**: student1, student2, student3, student4, student5, student6
   - **Coordinators**: coordinator_cse, coordinator_ece, coordinator_mech, coordinator_civil, coordinator_eee, coordinator_it
   - **HODs**: hod_cse, hod_ece, hod_mech, hod_civil, hod_eee, hod_it
   - **Director**: director

4. **Login Required**: All pages except login require authentication

## Organizational Structure

### Departments
- **CSE** (Computer Science & Engineering)
- **ECE** (Electronics & Communication Engineering)
- **MECH** (Mechanical Engineering)
- **CIVIL** (Civil Engineering)
- **EEE** (Electrical & Electronics Engineering)
- **IT** (Information Technology)

### Role Hierarchy
1. **Students** - Submit requests
2. **Class Coordinators** - First level approval, coordinate class activities
3. **HODs** - Department heads, second level approval for medium/complex requests
4. **Director** - Final authority for complex requests

## Dependencies

- Spring Boot Web Starter (REST controllers and web functionality)
- Spring Boot JPA Starter (database operations)
- Thymeleaf Template Engine (HTML templates)
- H2 Database (lightweight in-memory database)

## Usage

1. **Login**: Access http://localhost:8080 → Redirected to login page
2. **Authenticate**: Enter username and password (see test users above)
3. **Create Requests**: Students can submit various types of requests
4. **Coordinate**: Class coordinators review and approve requests (all types)
5. **Approve**: HODs make departmental decisions for medium and complex requests
6. **Final Approval**: Director approves complex institutional requests
7. **Track Status**: All users can view request progress and approval history
8. **Logout**: Use logout button to end session securely

## Security Notes

- **No User Switching**: Removed for security - users must logout and login with different credentials
- **Session Protection**: Sessions expire after 30 minutes of inactivity
- **Access Control**: All pages except login require valid authentication
- **Secure Cookies**: Session cookies are HTTP-only and use SameSite protection

## Technical Details

- **Java 17** with Spring Boot 3.2.0
- **H2 In-Memory Database** (data resets on restart)
- **Thymeleaf Templates** for responsive UI
- **Maven** for dependency management
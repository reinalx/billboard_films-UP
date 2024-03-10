    import {useSelector} from 'react-redux';
    import {Link} from 'react-router-dom';
    import {FormattedMessage} from 'react-intl';

    import users from '../../users';

    const Header = () => {

        const userName = useSelector(users.selectors.getUserName);
        const userType = useSelector(users.selectors.getUser);


        return (

            <nav className="navbar navbar-expand-lg navbar-light bg-light border">
                <Link className="navbar-brand" to="/">PA Project</Link>
                <button id="navbar-toggler" className="navbar-toggler" type="button"
                    data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">

                    <ul className="navbar-nav mr-auto">
                    </ul>

                    {userName ?

                    <ul className="navbar-nav">
                        {userType.role === "TICKET_SELLER"  && (
                            <li className="nav-item">
                                <Link id="nav-link" className="nav-link" to="/sale/deliverTicket">
                                    <FormattedMessage id="project.users.DeliverTicket"/>
                                </Link>
                            </li>
                        )}
                        {userType.role === "VIEWER"  && (
                            <li className="nav-item">
                                <Link id="nav-link" className="nav-link" to="/sale/findSales">
                                    <FormattedMessage id="project.sale.FindSales"/>
                                </Link>
                            </li>
                        )}


                        <li className="nav-item dropdown">

                            <a id="user"  className="dropdown-toggle nav-link" href="/"
                                data-toggle="dropdown">
                                <span className="fa-solid fa-user"></span>&nbsp;
                                {userName}
                            </a>
                            <div id="user-menu" className="dropdown-menu dropdown-menu-right">
                                <Link className="dropdown-item" to="/users/update-profile">
                                    <FormattedMessage id="project.users.UpdateProfile.title"/>
                                </Link>
                                <Link className="dropdown-item" to="/users/change-password">
                                    <FormattedMessage id="project.users.ChangePassword.title"/>
                                </Link>
                                <div className="dropdown-divider"></div>
                                <Link id="logout" className="dropdown-item" to="/users/logout">
                                    <FormattedMessage id="project.app.Header.logout"/>
                                </Link>
                            </div>

                        </li>

                    </ul>

                    :

                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link id="login-link" className="nav-link" to="/users/login">
                                <FormattedMessage id="project.users.Login.title"/>
                            </Link>
                        </li>
                    </ul>

                    }

                </div>
            </nav>

        );

    };

    export default Header;

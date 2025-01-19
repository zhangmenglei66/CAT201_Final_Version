$(document).ready(function() {
	// Form validation configuration
	const validationConfig = {
		username: {
			selector: '#userName',
			pattern: /^[a-zA-Z][a-zA-Z0-9]{3,7}$/,
			messages: {
				required: 'Please enter a username',
				format: 'Username must start with a letter and be 4-8 characters long',
				exists: 'Username already exists'
			},
			async validate(value) {
				try {
					const response = await $.get('jsp/admin/AdminManageServlet', {
						action: 'find',
						userName: value
					});
					return response.exists ? 'exists' : true;
				} catch (error) {
					console.error('Username validation error:', error);
					return 'Unable to verify username';
				}
			}
		},
		password: {
			selector: '#passWord',
			pattern: /^.{4,8}$/,
			messages: {
				required: 'Please enter a password',
				format: 'Password must be 4-8 characters long'
			}
		},
		confirmPassword: {
			selector: '#c_passWord',
			messages: {
				required: 'Please confirm your password',
				mismatch: 'Passwords do not match'
			},
			validate(value, form) {
				return value === $(form).find('#passWord').val() ? true : 'mismatch';
			}
		},
		name: {
			selector: '#name',
			pattern: /^.{2,8}$/,
			messages: {
				required: 'Please enter your name',
				format: 'Name must be 2-8 characters long'
			}
		}
	};

	// Initialize form validation
	class FormValidator {
		constructor(formSelector, config) {
			this.form = $(formSelector);
			this.config = config;
			this.setupValidation();
		}

		setupValidation() {
			this.form.on('submit', (e) => this.handleSubmit(e));

			// Setup real-time validation
			Object.keys(this.config).forEach(field => {
				const { selector } = this.config[field];
				$(selector).on('blur', (e) => this.validateField(field, e.target));
			});
		}

		async validateField(fieldName, element) {
			const config = this.config[fieldName];
			const $element = $(element);
			const value = $element.val().trim();
			const $error = $element.next('.error-message');

			// Clear previous error
			this.showError($element, '');

			// Required check
			if (!value) {
				this.showError($element, config.messages.required);
				return false;
			}

			// Pattern check
			if (config.pattern && !config.pattern.test(value)) {
				this.showError($element, config.messages.format);
				return false;
			}

			// Custom validation
			if (config.validate) {
				const result = await config.validate(value, this.form[0]);
				if (result !== true) {
					this.showError($element, config.messages[result] || result);
					return false;
				}
			}

			return true;
		}

		showError($element, message) {
			let $error = $element.next('.error-message');
			if (!$error.length) {
				$error = $('<div class="error-message"></div>').insertAfter($element);
			}
			$error.text(message).toggleClass('active', !!message);
		}

		async handleSubmit(e) {
			e.preventDefault();

			const validations = Object.keys(this.config).map(field =>
				this.validateField(field, $(this.config[field].selector)[0])
			);

			const results = await Promise.all(validations);
			if (results.every(result => result === true)) {
				this.form[0].submit();
			}
		}
	}

	// Initialize form validation
	const validator = new FormValidator('#myForm', validationConfig);
});